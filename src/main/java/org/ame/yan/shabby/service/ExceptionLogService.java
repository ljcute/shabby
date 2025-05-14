package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.ExceptionLogDTO;
import org.ame.yan.shabby.entity.ExceptionLog;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ExceptionLogService extends IService<ExceptionLog> {

    PageResultDTO<ExceptionLogDTO> listExceptionLogs(ConditionVO conditionVO);

}
