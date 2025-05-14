package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.LabelOptionDTO;
import org.ame.yan.shabby.model.dto.MenuDTO;
import org.ame.yan.shabby.model.dto.UserMenuDTO;
import org.ame.yan.shabby.entity.Menu;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.vo.IsHiddenVO;
import org.ame.yan.shabby.model.vo.MenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<MenuDTO> listMenus(ConditionVO conditionVO);

    void saveOrUpdateMenu(MenuVO menuVO);

    void updateMenuIsHidden(IsHiddenVO isHiddenVO);

    void deleteMenu(Integer menuId);

    List<LabelOptionDTO> listMenuOptions();

    List<UserMenuDTO> listUserMenus();

}
