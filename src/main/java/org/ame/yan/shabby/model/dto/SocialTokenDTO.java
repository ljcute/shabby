package org.ame.yan.shabby.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialTokenDTO {

    private String openId;

    private String accessToken;

    private Integer loginType;

}
