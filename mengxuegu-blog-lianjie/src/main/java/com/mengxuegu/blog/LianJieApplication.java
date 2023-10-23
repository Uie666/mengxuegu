package com.mengxuegu.blog;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients // 扫描 @Feign 接口进行远程调用
@EnableDiscoveryClient
@EnableSwagger2Doc
@SpringBootApplication
public class LianJieApplication {

    public static void main(String[] args) {
        SpringApplication.run(LianJieApplication.class, args);
    }
}
