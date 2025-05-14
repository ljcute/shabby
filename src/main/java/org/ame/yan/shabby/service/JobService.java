package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.JobDTO;
import org.ame.yan.shabby.entity.Job;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import org.ame.yan.shabby.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface JobService extends IService<Job> {

    void saveJob(JobVO jobVO);

    void updateJob(JobVO jobVO);

    void deleteJobs(List<Integer> tagIds);

    JobDTO getJobById(Integer jobId);

    PageResultDTO<JobDTO> listJobs(JobSearchVO jobSearchVO);

    void updateJobStatus(JobStatusVO jobStatusVO);

    void runJob(JobRunVO jobRunVO);

    List<String> listJobGroups();

}
