package org.ame.yan.shabby.controller;

import org.ame.yan.shabby.annotation.OptLog;
import org.ame.yan.shabby.model.dto.OperationLogDTO;
import org.ame.yan.shabby.model.vo.ResultVO;
import org.ame.yan.shabby.service.OperationLogService;
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

@Api(tags = "操作日志模块")
@RestController
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @ApiOperation(value = "查看操作日志")
    @GetMapping("/admin/operation/logs")
    public ResultVO<PageResultDTO<OperationLogDTO>> listOperationLogs(ConditionVO conditionVO) {
        return ResultVO.ok(operationLogService.listOperationLogs(conditionVO));
    }

    @OptLog(optType = DELETE)
    @ApiOperation(value = "删除操作日志")
    @DeleteMapping("/admin/operation/logs")
    public ResultVO<?> deleteOperationLogs(@RequestBody List<Integer> operationLogIds) {
        operationLogService.removeByIds(operationLogIds);
        return ResultVO.ok();
    }

}
