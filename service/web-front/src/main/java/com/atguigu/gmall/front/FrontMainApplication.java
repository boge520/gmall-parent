package com.atguigu.gmall.front;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//说明接下来所有的feignclient在哪个包
@EnableFeignClients(basePackages = "com.atguigu.gmall.feign")
@SpringCloudApplication
public class FrontMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontMainApplication.class,args);
    }
}
