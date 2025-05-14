package org.ame.yan.shabby.quartz;

import org.ame.yan.shabby.constant.ScheduleConstant;
import org.ame.yan.shabby.entity.Job;
import org.ame.yan.shabby.entity.JobLog;
import org.ame.yan.shabby.mapper.JobLogMapper;
import org.ame.yan.shabby.util.ExceptionUtil;
import org.ame.yan.shabby.util.SpringUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;

import static org.ame.yan.shabby.constant.CommonConstant.ONE;
import static org.ame.yan.shabby.constant.CommonConstant.ZERO;

public abstract class AbstractQuartzJob implements org.quartz.Job {

    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    private static final ThreadLocal<Date> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Job job = new Job();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleConstant.TASK_PROPERTIES), job);
        try {
            before(context, job);
            doExecute(context, job);
            after(context, job, null);
        } catch (Exception e) {
            log.error("任务执行异常:", e);
            after(context, job, e);
        }
    }

    protected void before(JobExecutionContext context, Job job) {
        THREAD_LOCAL.set(new Date());
    }

    protected void after(JobExecutionContext context, Job job, Exception e) {
        Date startTime = THREAD_LOCAL.get();
        THREAD_LOCAL.remove();
        final JobLog jobLog = new JobLog();
        jobLog.setJobId(job.getId());
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setInvokeTarget(job.getInvokeTarget());
        jobLog.setStartTime(startTime);
        jobLog.setEndTime(new Date());
        long runMs = jobLog.getEndTime().getTime() - jobLog.getStartTime().getTime();
        jobLog.setJobMessage(jobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null) {
            jobLog.setStatus(ZERO);
            jobLog.setExceptionInfo(ExceptionUtil.getTrace(e));
        } else {
            jobLog.setStatus(ONE);
        }
        SpringUtil.getBean(JobLogMapper.class).insert(jobLog);
    }

    protected abstract void doExecute(JobExecutionContext context, Job job) throws Exception;
}
