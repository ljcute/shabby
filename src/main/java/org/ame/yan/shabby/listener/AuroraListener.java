package org.ame.yan.shabby.listener;

import org.ame.yan.shabby.entity.ExceptionLog;
import org.ame.yan.shabby.entity.OperationLog;
import org.ame.yan.shabby.event.ExceptionLogEvent;
import org.ame.yan.shabby.event.OperationLogEvent;
import org.ame.yan.shabby.mapper.ExceptionLogMapper;
import org.ame.yan.shabby.mapper.OperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuroraListener {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Async
    @EventListener(OperationLogEvent.class)
    public void saveOperationLog(OperationLogEvent operationLogEvent) {
        operationLogMapper.insert((OperationLog) operationLogEvent.getSource());
    }

    @Async
    @EventListener(ExceptionLogEvent.class)
    public void saveExceptionLog(ExceptionLogEvent exceptionLogEvent) {
        exceptionLogMapper.insert((ExceptionLog) exceptionLogEvent.getSource());
    }

}
