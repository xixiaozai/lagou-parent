package com.xu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer // 开启配置中心功能
public class CloudConfigApplication9006 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication9006.class, args);
    }
}
