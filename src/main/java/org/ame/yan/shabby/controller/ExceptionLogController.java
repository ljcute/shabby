package org.ame.yan.shabby.controller;

import org.ame.yan.shabby.annotation.OptLog;
import org.ame.yan.shabby.model.dto.ExceptionLogDTO;
import org.ame.yan.shabby.model.vo.ResultVO;
import org.ame.yan.shabby.service.ExceptionLogService;
import org.ame.yan.shabby.model.vo.ConditionVO;
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

@Api(tags = "异常日志模块")
@RestController
public class ExceptionLogController {

    @Autowired
    private ExceptionLogService exceptionLogService;

    @ApiOperation("获取异常日志")
    @GetMapping("/admin/exception/logs")
    public ResultVO<PageResultDTO<ExceptionLogDTO>> listExceptionLogs(ConditionVO conditionVO) {
        return ResultVO.ok(exceptionLogService.listExceptionLogs(conditionVO));
    }

    @OptLog(optType = DELETE)
    @ApiOperation(value = "删除异常日志")
    @DeleteMapping("/admin/exception/logs")
    public ResultVO<?> deleteExceptionLogs(@RequestBody List<Integer> exceptionLogIds) {
        exceptionLogService.removeByIds(exceptionLogIds);
        return ResultVO.ok();
    }

}
