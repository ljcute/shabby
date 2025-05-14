package org.ame.yan.shabby.mapper;

import org.ame.yan.shabby.model.dto.PhotoAlbumAdminDTO;
import org.ame.yan.shabby.entity.PhotoAlbum;
import org.ame.yan.shabby.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoAlbumMapper extends BaseMapper<PhotoAlbum> {

    List<PhotoAlbumAdminDTO> listPhotoAlbumsAdmin(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO conditionVO);

}
