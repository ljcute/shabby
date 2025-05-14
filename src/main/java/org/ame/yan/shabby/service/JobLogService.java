package org.ame.yan.shabby.service;


import org.ame.yan.shabby.model.dto.JobLogDTO;
import org.ame.yan.shabby.entity.JobLog;
import org.ame.yan.shabby.model.vo.JobLogSearchVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface JobLogService extends IService<JobLog> {

    PageResultDTO<JobLogDTO> listJobLogs(JobLogSearchVO jobLogSearchVO);

    void deleteJobLogs(List<Integer> ids);

    void cleanJobLogs();

    List<String> listJobLogGroups();

}
