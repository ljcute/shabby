package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.OperationLogDTO;
import org.ame.yan.shabby.entity.OperationLog;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OperationLogService extends IService<OperationLog> {

    PageResultDTO<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}
