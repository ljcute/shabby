package org.ame.yan.shabby.controller;

import org.ame.yan.shabby.annotation.OptLog;
import org.ame.yan.shabby.model.dto.AboutDTO;
import org.ame.yan.shabby.model.dto.AuroraAdminInfoDTO;
import org.ame.yan.shabby.model.dto.AuroraHomeInfoDTO;
import org.ame.yan.shabby.model.dto.WebsiteConfigDTO;
import org.ame.yan.shabby.enums.FilePathEnum;
import org.ame.yan.shabby.model.vo.ResultVO;
import org.ame.yan.shabby.service.AuroraInfoService;
import org.ame.yan.shabby.strategy.context.UploadStrategyContext;
import org.ame.yan.shabby.model.vo.AboutVO;
import org.ame.yan.shabby.model.vo.WebsiteConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static org.ame.yan.shabby.constant.OptTypeConstant.UPDATE;
import static org.ame.yan.shabby.constant.OptTypeConstant.UPLOAD;

@Api(tags = "aurora信息")
@RestController
public class AuroraInfoController {

    @Autowired
    private AuroraInfoService auroraInfoService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @ApiOperation(value = "上报访客信息")
    @PostMapping("/report")
    public ResultVO<?> report() {
        auroraInfoService.report();
        return ResultVO.ok();
    }

    @ApiOperation(value = "获取系统信息")
    @GetMapping("/")
    public ResultVO<AuroraHomeInfoDTO> getBlogHomeInfo() {
        return ResultVO.ok(auroraInfoService.getAuroraHomeInfo());
    }

    @ApiOperation(value = "获取系统后台信息")
    @GetMapping("/admin")
    public ResultVO<AuroraAdminInfoDTO> getBlogBackInfo() {
        return ResultVO.ok(auroraInfoService.getAuroraAdminInfo());
    }

    @OptLog(optType = UPDATE)
    @ApiOperation(value = "更新网站配置")
    @PutMapping("/admin/website/config")
    public ResultVO<?> updateWebsiteConfig(@Valid @RequestBody WebsiteConfigVO websiteConfigVO) {
        auroraInfoService.updateWebsiteConfig(websiteConfigVO);
        return ResultVO.ok();
    }

    @ApiOperation(value = "获取网站配置")
    @GetMapping("/admin/website/config")
    public ResultVO<WebsiteConfigDTO> getWebsiteConfig() {
        return ResultVO.ok(auroraInfoService.getWebsiteConfig());
    }

    @ApiOperation(value = "查看关于我信息")
    @GetMapping("/about")
    public ResultVO<AboutDTO> getAbout() {
        return ResultVO.ok(auroraInfoService.getAbout());
    }

    @OptLog(optType = UPDATE)
    @ApiOperation(value = "修改关于我信息")
    @PutMapping("/admin/about")
    public ResultVO<?> updateAbout(@Valid @RequestBody AboutVO aboutVO) {
        auroraInfoService.updateAbout(aboutVO);
        return ResultVO.ok();
    }

    @OptLog(optType = UPLOAD)
    @ApiOperation(value = "上传博客配置图片")
    @ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/config/images")
    public ResultVO<String> savePhotoAlbumCover(MultipartFile file) {
        return ResultVO.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CONFIG.getPath()));
    }

}
