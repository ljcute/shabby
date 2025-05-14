package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.*;
import org.ame.yan.shabby.model.vo.*;

import java.util.List;

public interface UserAuthService {

    void sendCode(String username);

    List<UserAreaDTO> listUserAreas(ConditionVO conditionVO);

    void register(UserVO userVO);

    void updatePassword(UserVO userVO);

    void updateAdminPassword(PasswordVO passwordVO);

    PageResultDTO<UserAdminDTO> listUsers(ConditionVO condition);

    UserLogoutStatusDTO logout();

    UserInfoDTO qqLogin(QQLoginVO qqLoginVO);

}
