package com.xu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // 开启feign功能
public class SpringBootApplication8091 {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication8091.class, args);
    }

}
