package com.xu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author xushiwei
 * @Date 2022/11/9 11:51
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication8762 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication8762.class, args);
    }
}
