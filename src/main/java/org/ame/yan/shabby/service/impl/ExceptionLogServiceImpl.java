package org.ame.yan.shabby.service.impl;

import org.ame.yan.shabby.model.dto.ExceptionLogDTO;
import org.ame.yan.shabby.entity.ExceptionLog;
import org.ame.yan.shabby.mapper.ExceptionLogMapper;
import org.ame.yan.shabby.service.ExceptionLogService;
import org.ame.yan.shabby.util.BeanCopyUtil;
import org.ame.yan.shabby.util.PageUtil;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {

    @Override
    public PageResultDTO<ExceptionLogDTO> listExceptionLogs(ConditionVO conditionVO) {
        Page<ExceptionLog> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        Page<ExceptionLog> exceptionLogPage = this.page(page, new LambdaQueryWrapper<ExceptionLog>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), ExceptionLog::getOptDesc, conditionVO.getKeywords())
                .orderByDesc(ExceptionLog::getId));
        List<ExceptionLogDTO> exceptionLogDTOs = BeanCopyUtil.copyList(exceptionLogPage.getRecords(), ExceptionLogDTO.class);
        return new PageResultDTO<>(exceptionLogDTOs, (int) exceptionLogPage.getTotal());
    }

}
