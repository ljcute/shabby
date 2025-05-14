package org.ame.yan.shabby.mapper;

import org.ame.yan.shabby.model.dto.TagAdminDTO;
import org.ame.yan.shabby.model.dto.TagDTO;
import org.ame.yan.shabby.entity.Tag;
import org.ame.yan.shabby.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    List<TagDTO> listTags();

    List<TagDTO> listTopTenTags();

    List<String> listTagNamesByArticleId(Integer articleId);

    List<TagAdminDTO> listTagsAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

}
