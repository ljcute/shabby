package org.ame.yan.shabby.quartz;

import org.ame.yan.shabby.entity.Job;
import org.ame.yan.shabby.util.JobInvokeUtil;
import org.quartz.JobExecutionContext;

public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
