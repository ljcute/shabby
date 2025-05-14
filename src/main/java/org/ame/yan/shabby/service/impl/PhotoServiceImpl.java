package org.ame.yan.shabby.service.impl;


import org.ame.yan.shabby.model.dto.PageResultDTO;
import org.ame.yan.shabby.model.dto.PhotoAdminDTO;
import org.ame.yan.shabby.model.dto.PhotoAlbumAdminDTO;
import org.ame.yan.shabby.model.dto.PhotoDTO;
import org.ame.yan.shabby.entity.Photo;
import org.ame.yan.shabby.entity.PhotoAlbum;
import org.ame.yan.shabby.exception.BizException;
import org.ame.yan.shabby.mapper.PhotoAlbumMapper;
import org.ame.yan.shabby.mapper.PhotoMapper;
import org.ame.yan.shabby.service.PhotoAlbumService;
import org.ame.yan.shabby.service.PhotoService;
import org.ame.yan.shabby.util.BeanCopyUtil;
import org.ame.yan.shabby.util.PageUtil;
import org.ame.yan.shabby.model.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.ame.yan.shabby.constant.CommonConstant.FALSE;
import static org.ame.yan.shabby.enums.PhotoAlbumStatusEnum.PUBLIC;

@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private PhotoAlbumService photoAlbumService;

    @Override
    public PageResultDTO<PhotoAdminDTO> listPhotos(ConditionVO conditionVO) {
        Page<Photo> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        Page<Photo> photoPage = photoMapper.selectPage(page, new LambdaQueryWrapper<Photo>()
                .eq(Objects.nonNull(conditionVO.getAlbumId()), Photo::getAlbumId, conditionVO.getAlbumId())
                .eq(Photo::getIsDelete, conditionVO.getIsDelete())
                .orderByDesc(Photo::getId)
                .orderByDesc(Photo::getUpdateTime));
        List<PhotoAdminDTO> photos = BeanCopyUtil.copyList(photoPage.getRecords(), PhotoAdminDTO.class);
        return new PageResultDTO<>(photos, (int) photoPage.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePhoto(PhotoInfoVO photoInfoVO) {
        Photo photo = BeanCopyUtil.copyObject(photoInfoVO, Photo.class);
        photoMapper.updateById(photo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void savePhotos(PhotoVO photoVO) {
        List<Photo> photoList = photoVO.getPhotoUrls().stream().map(item -> Photo.builder()
                        .albumId(photoVO.getAlbumId())
                        .photoName(IdWorker.getIdStr())
                        .photoSrc(item)
                        .build())
                .collect(Collectors.toList());
        this.saveBatch(photoList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePhotosAlbum(PhotoVO photoVO) {
        List<Photo> photoList = photoVO.getPhotoIds().stream().map(item -> Photo.builder()
                        .id(item)
                        .albumId(photoVO.getAlbumId())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(photoList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePhotoDelete(DeleteVO deleteVO) {
        List<Photo> photoList = deleteVO.getIds().stream().map(item -> Photo.builder()
                        .id(item)
                        .isDelete(deleteVO.getIsDelete())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(photoList);
        if (deleteVO.getIsDelete().equals(FALSE)) {
            List<PhotoAlbum> photoAlbumList = photoMapper.selectList(new LambdaQueryWrapper<Photo>()
                            .select(Photo::getAlbumId)
                            .in(Photo::getId, deleteVO.getIds())
                            .groupBy(Photo::getAlbumId))
                    .stream()
                    .map(item -> PhotoAlbum.builder()
                            .id(item.getAlbumId())
                            .isDelete(FALSE)
                            .build())
                    .collect(Collectors.toList());
            photoAlbumService.updateBatchById(photoAlbumList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePhotos(List<Integer> photoIds) {
        photoMapper.deleteBatchIds(photoIds);
    }

    @Override
    public PhotoDTO listPhotosByAlbumId(Integer albumId) {
        PhotoAlbum photoAlbum = photoAlbumService.getOne(new LambdaQueryWrapper<PhotoAlbum>()
                .eq(PhotoAlbum::getId, albumId)
                .eq(PhotoAlbum::getIsDelete, FALSE)
                .eq(PhotoAlbum::getStatus, PUBLIC.getStatus()));
        if (Objects.isNull(photoAlbum)) {
            throw new BizException("相册不存在");
        }
        Page<Photo> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<String> photos = photoMapper.selectPage(page, new LambdaQueryWrapper<Photo>()
                        .select(Photo::getPhotoSrc)
                        .eq(Photo::getAlbumId, albumId)
                        .eq(Photo::getIsDelete, FALSE)
                        .orderByDesc(Photo::getId))
                .getRecords()
                .stream()
                .map(Photo::getPhotoSrc)
                .collect(Collectors.toList());
        return PhotoDTO.builder()
                .photoAlbumCover(photoAlbum.getAlbumCover())
                .photoAlbumName(photoAlbum.getAlbumName())
                .photos(photos)
                .build();
    }

}
