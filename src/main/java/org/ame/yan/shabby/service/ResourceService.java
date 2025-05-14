package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.LabelOptionDTO;
import org.ame.yan.shabby.model.dto.ResourceDTO;
import org.ame.yan.shabby.entity.Resource;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.vo.ResourceVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    void importSwagger();

    void saveOrUpdateResource(ResourceVO resourceVO);

    void deleteResource(Integer resourceId);

    List<ResourceDTO> listResources(ConditionVO conditionVO);

    List<LabelOptionDTO> listResourceOption();

}
