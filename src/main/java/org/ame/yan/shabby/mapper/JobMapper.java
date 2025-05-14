package org.ame.yan.shabby.mapper;

import org.ame.yan.shabby.model.dto.JobDTO;
import org.ame.yan.shabby.entity.Job;
import org.ame.yan.shabby.model.vo.JobSearchVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMapper extends BaseMapper<Job> {

    Integer countJobs(@Param("jobSearchVO") JobSearchVO jobSearchVO);

    List<JobDTO> listJobs(@Param("current") Long current, @Param("size") Long size, @Param("jobSearchVO")JobSearchVO jobSearchVO);

    List<String> listJobGroups();

}
