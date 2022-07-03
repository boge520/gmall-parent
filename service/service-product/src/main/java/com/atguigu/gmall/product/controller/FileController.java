package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/admin/product/")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/fileUpload")
    public Result fileUpload(HttpServletRequest request,
                             @RequestPart("file") MultipartFile file) throws Exception {

        //1、文件上传到minio，并拿到文件访问地址
        String url = fileService.upload(file);
        return Result.ok(url);
    }
}
