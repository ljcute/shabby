package org.ame.yan.shabby.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static org.ame.yan.shabby.enums.ZoneEnum.SHANGHAI;


/**
 * Author:  ame.yan
 * Date:  2025/5/14 20:48
 */

@Configuration
public class GlobalZoneConfig {

    @PostConstruct
    public void setGlobalZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(SHANGHAI.getZone()));
    }

}
