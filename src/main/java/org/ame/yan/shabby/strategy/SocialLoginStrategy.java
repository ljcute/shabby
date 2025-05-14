package org.ame.yan.shabby.strategy;


import org.ame.yan.shabby.model.dto.UserInfoDTO;

public interface SocialLoginStrategy {

    UserInfoDTO login(String data);

}
