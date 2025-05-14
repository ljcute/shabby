package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.AboutDTO;
import org.ame.yan.shabby.model.dto.AuroraAdminInfoDTO;
import org.ame.yan.shabby.model.dto.AuroraHomeInfoDTO;
import org.ame.yan.shabby.model.dto.WebsiteConfigDTO;
import org.ame.yan.shabby.model.vo.AboutVO;
import org.ame.yan.shabby.model.vo.WebsiteConfigVO;

public interface AuroraInfoService {

    void report();

    AuroraHomeInfoDTO getAuroraHomeInfo();

    AuroraAdminInfoDTO getAuroraAdminInfo();

    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    WebsiteConfigDTO getWebsiteConfig();

    void updateAbout(AboutVO aboutVO);

    AboutDTO getAbout();

}
