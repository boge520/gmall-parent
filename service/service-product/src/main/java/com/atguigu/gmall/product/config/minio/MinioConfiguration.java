package com.atguigu.gmall.product.config.minio;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MinioConfiguration {

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient minioClient = new MinioClient(
                minioProperties.getEndpoint(),
                minioProperties.getAccessKey(),
                minioProperties.getSecretKey()
        );
        String bucketName = minioProperties.getBucketName();
        log.info("该bucketName为：",bucketName);
        if (!minioClient.bucketExists(bucketName)) {
            log.info("minio指定的bucket[{}]，不存在，正在自动创建",bucketName);
            minioClient.makeBucket(bucketName);
        }
        return minioClient;
    }
}
