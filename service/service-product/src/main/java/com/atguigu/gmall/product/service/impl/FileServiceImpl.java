package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.product.config.minio.MinioProperties;
import com.atguigu.gmall.product.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioProperties minioProperties;
    @Autowired
    private MinioClient minioClient;
    @Value("${app.minio.bucketName}")
    private String bucketName;

    @Override
    public String upload(MultipartFile file) throws Exception{
        String filename = UUID.randomUUID().toString().replaceAll("-", "")+"_"+file.getOriginalFilename();

        //1、准备上传
        PutObjectOptions options = new PutObjectOptions(file.getSize(),-1);
        options.setContentType(file.getContentType());
        minioClient.putObject(bucketName,filename,file.getInputStream(),options);

        //http://192.168.79.130:9000/gmall/123.jpg
        String url = minioProperties.getEndpoint()+"/"+bucketName+"/"+filename;
        return url;
    }
}
