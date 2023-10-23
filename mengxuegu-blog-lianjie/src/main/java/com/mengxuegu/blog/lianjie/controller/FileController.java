package com.mengxuegu.blog.lianjie.controller;

import com.mengxuegu.blog.util.aliyun.AliyunUtil;
import com.mengxuegu.blog.util.base.Result;
import com.mengxuegu.blog.util.enums.PlatformEnum;
import com.mengxuegu.blog.util.properties.AliyunProperties;
import com.mengxuegu.blog.util.properties.BlogProperties;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "简历管理接口", description = "简历管理接口，提供简历的上传或删除图片文件")
@RequestMapping("/file")
@RestController
public class FileController {
    @Autowired
    private BlogProperties blogProperties;

    public Result upload(@RequestParam("file") MultipartFile file) {
        AliyunProperties aliyun = blogProperties.getAliyun();
        return AliyunUtil.uploadFileToOss(PlatformEnum.ARTICEL, file, aliyun);
    }
}
