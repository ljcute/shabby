package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.UniqueViewDTO;
import org.ame.yan.shabby.entity.UniqueView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UniqueViewService extends IService<UniqueView> {

    List<UniqueViewDTO> listUniqueViews();

}
