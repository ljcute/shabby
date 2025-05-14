package org.ame.yan.shabby.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * Author:  ame.yan
 * Date:  2025/5/14 20:50
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "qq")
public class QQConfigProperties {

    private String appId;

    private String checkTokenUrl;

    private String userInfoUrl;

}
