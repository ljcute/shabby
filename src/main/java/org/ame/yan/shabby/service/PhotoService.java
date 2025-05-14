package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.PageResultDTO;
import org.ame.yan.shabby.model.dto.PhotoAdminDTO;
import org.ame.yan.shabby.model.dto.PhotoAlbumAdminDTO;
import org.ame.yan.shabby.model.dto.PhotoDTO;
import org.ame.yan.shabby.entity.Photo;
import org.ame.yan.shabby.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PhotoService extends IService<Photo> {

    PageResultDTO<PhotoAdminDTO> listPhotos(ConditionVO conditionVO);

    void updatePhoto(PhotoInfoVO photoInfoVO);

    void savePhotos(PhotoVO photoVO);

    void updatePhotosAlbum(PhotoVO photoVO);

    void updatePhotoDelete(DeleteVO deleteVO);

    void deletePhotos(List<Integer> photoIds);

    PhotoDTO listPhotosByAlbumId(Integer albumId);

}
