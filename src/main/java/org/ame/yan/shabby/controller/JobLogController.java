package org.ame.yan.shabby.controller;

import org.ame.yan.shabby.annotation.OptLog;
import org.ame.yan.shabby.model.dto.JobLogDTO;
import org.ame.yan.shabby.model.vo.ResultVO;
import org.ame.yan.shabby.service.JobLogService;
import org.ame.yan.shabby.model.vo.JobLogSearchVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.ame.yan.shabby.constant.OptTypeConstant.DELETE;

@Api(tags = "定时任务日志模块")
@RestController
public class JobLogController {

    @Autowired
    private JobLogService jobLogService;

    @ApiOperation("获取定时任务的日志列表")
    @GetMapping("/admin/jobLogs")
    public ResultVO<PageResultDTO<JobLogDTO>> listJobLogs(JobLogSearchVO jobLogSearchVO) {
        return ResultVO.ok(jobLogService.listJobLogs(jobLogSearchVO));
    }

    @OptLog(optType = DELETE)
    @ApiOperation("删除定时任务的日志")
    @DeleteMapping("/admin/jobLogs")
    public ResultVO<?> deleteJobLogs(@RequestBody List<Integer> ids) {
        jobLogService.deleteJobLogs(ids);
        return ResultVO.ok();
    }

    @OptLog(optType = DELETE)
    @ApiOperation("清除定时任务的日志")
    @DeleteMapping("/admin/jobLogs/clean")
    public ResultVO<?> cleanJobLogs() {
        jobLogService.cleanJobLogs();
        return ResultVO.ok();
    }

    @ApiOperation("获取定时任务日志的所有组名")
    @GetMapping("/admin/jobLogs/jobGroups")
    public ResultVO<?> listJobLogGroups() {
        return ResultVO.ok(jobLogService.listJobLogGroups());
    }
}
