package org.ame.yan.shabby.mapper;

import org.ame.yan.shabby.model.dto.ResourceRoleDTO;
import org.ame.yan.shabby.model.dto.RoleDTO;
import org.ame.yan.shabby.entity.Role;
import org.ame.yan.shabby.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<ResourceRoleDTO> listResourceRoles();

    List<String> listRolesByUserInfoId(@Param("userInfoId") Integer userInfoId);

    List<RoleDTO> listRoles(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

}
