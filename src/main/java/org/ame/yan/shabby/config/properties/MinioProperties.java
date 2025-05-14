package org.ame.yan.shabby.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * Author:  ame.yan
 * Date:  2025/5/14 20:49
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "upload.minio")
public class MinioProperties {

    private String url;

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
