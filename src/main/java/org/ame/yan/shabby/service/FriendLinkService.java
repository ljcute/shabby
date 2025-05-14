package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.FriendLinkAdminDTO;
import org.ame.yan.shabby.model.dto.FriendLinkDTO;
import org.ame.yan.shabby.entity.FriendLink;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.vo.FriendLinkVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FriendLinkService extends IService<FriendLink> {

    List<FriendLinkDTO> listFriendLinks();

    PageResultDTO<FriendLinkAdminDTO> listFriendLinksAdmin(ConditionVO conditionVO);

    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}
