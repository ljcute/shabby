package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.RoleDTO;
import org.ame.yan.shabby.model.dto.UserRoleDTO;
import org.ame.yan.shabby.entity.Role;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import org.ame.yan.shabby.model.vo.RoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<UserRoleDTO> listUserRoles();

    PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO);

    void saveOrUpdateRole(RoleVO roleVO);

    void deleteRoles(List<Integer> ids);

}
