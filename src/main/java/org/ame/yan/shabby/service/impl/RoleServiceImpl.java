package org.ame.yan.shabby.service.impl;

import org.ame.yan.shabby.constant.CommonConstant;
import org.ame.yan.shabby.model.dto.RoleDTO;
import org.ame.yan.shabby.model.dto.UserRoleDTO;
import org.ame.yan.shabby.entity.Role;
import org.ame.yan.shabby.entity.RoleMenu;
import org.ame.yan.shabby.entity.RoleResource;
import org.ame.yan.shabby.entity.UserRole;
import org.ame.yan.shabby.exception.BizException;
import org.ame.yan.shabby.handler.FilterInvocationSecurityMetadataSourceImpl;
import org.ame.yan.shabby.mapper.RoleMapper;
import org.ame.yan.shabby.mapper.UserRoleMapper;
import org.ame.yan.shabby.service.RoleMenuService;
import org.ame.yan.shabby.service.RoleResourceService;
import org.ame.yan.shabby.service.RoleService;
import org.ame.yan.shabby.util.BeanCopyUtil;
import org.ame.yan.shabby.util.PageUtil;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import org.ame.yan.shabby.model.vo.RoleVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

    @Override
    public List<UserRoleDTO> listUserRoles() {
        List<Role> roleList = roleMapper.selectList(new LambdaQueryWrapper<Role>()
                .select(Role::getId, Role::getRoleName));
        return BeanCopyUtil.copyList(roleList, UserRoleDTO.class);
    }

    @SneakyThrows
    @Override
    public PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), Role::getRoleName, conditionVO.getKeywords());
        CompletableFuture<Integer> asyncCount = CompletableFuture.supplyAsync(() -> roleMapper.selectCount(queryWrapper));
        List<RoleDTO> roleDTOs = roleMapper.listRoles(PageUtil.getLimitCurrent(), PageUtil.getSize(), conditionVO);
        return new PageResultDTO<>(roleDTOs, asyncCount.get());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateRole(RoleVO roleVO) {
        Role roleCheck = roleMapper.selectOne(new LambdaQueryWrapper<Role>()
                .select(Role::getId)
                .eq(Role::getRoleName, roleVO.getRoleName()));
        if (Objects.nonNull(roleCheck) && !(roleCheck.getId().equals(roleVO.getId()))) {
            throw new BizException("该角色存在");
        }
        Role role = Role.builder()
                .id(roleVO.getId())
                .roleName(roleVO.getRoleName())
                .isDisable(CommonConstant.FALSE)
                .build();
        this.saveOrUpdate(role);
        if (Objects.nonNull(roleVO.getResourceIds())) {
            if (Objects.nonNull(roleVO.getId())) {
                roleResourceService.remove(new LambdaQueryWrapper<RoleResource>()
                        .eq(RoleResource::getRoleId, roleVO.getId()));
            }
            List<RoleResource> roleResourceList = roleVO.getResourceIds().stream()
                    .map(resourceId -> RoleResource.builder()
                            .roleId(role.getId())
                            .resourceId(resourceId)
                            .build())
                    .collect(Collectors.toList());
            roleResourceService.saveBatch(roleResourceList);
            filterInvocationSecurityMetadataSource.clearDataSource();
        }
        if (Objects.nonNull(roleVO.getMenuIds())) {
            if (Objects.nonNull(roleVO.getId())) {
                roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleVO.getId()));
            }
            List<RoleMenu> roleMenuList = roleVO.getMenuIds().stream()
                    .map(menuId -> RoleMenu.builder()
                            .roleId(role.getId())
                            .menuId(menuId)
                            .build())
                    .collect(Collectors.toList());
            roleMenuService.saveBatch(roleMenuList);
        }
    }

    @Override
    public void deleteRoles(List<Integer> roleIdList) {
        Integer count = userRoleMapper.selectCount(new LambdaQueryWrapper<UserRole>()
                .in(UserRole::getRoleId, roleIdList));
        if (count > 0) {
            throw new BizException("该角色下存在用户");
        }
        roleMapper.deleteBatchIds(roleIdList);
    }

}
