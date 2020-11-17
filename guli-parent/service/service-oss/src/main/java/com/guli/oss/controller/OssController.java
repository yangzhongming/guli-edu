package com.guli.oss.controller;

import com.guli.commonutils.R;
import com.guli.oss.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/edu/oss/file")
@CrossOrigin
@Api(description="阿里云文件管理")
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
