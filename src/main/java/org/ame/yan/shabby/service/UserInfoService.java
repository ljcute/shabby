package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.PageResultDTO;
import org.ame.yan.shabby.model.dto.UserInfoDTO;
import org.ame.yan.shabby.model.dto.UserOnlineDTO;
import org.ame.yan.shabby.entity.UserInfo;
import org.ame.yan.shabby.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService extends IService<UserInfo> {

    void updateUserInfo(UserInfoVO userInfoVO);

    String updateUserAvatar(MultipartFile file);

    void saveUserEmail(EmailVO emailVO);

    void updateUserSubscribe(SubscribeVO subscribeVO);

    void updateUserRole(UserRoleVO userRoleVO);

    void updateUserDisable(UserDisableVO userDisableVO);

    PageResultDTO<UserOnlineDTO> listOnlineUsers(ConditionVO conditionVO);

    void removeOnlineUser(Integer userInfoId);

    UserInfoDTO getUserInfoById(Integer id);

}
