package com.xu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author xushiwei
 * @Date 2022/11/8 15:43
 * @Version 1.0
 */
@SpringBootApplication
@EntityScan("com.xu.pojo")
@EnableDiscoveryClient
//@EnableEurekaClient
public class ResumeApplication8080 {
    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication8080.class, args);
    }
}
