package com.xu.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLog {

    /**
     * 设置日志级别
     * @return
     */
    @Bean
    Logger.Level feignLevel(){
        return Logger.Level.FULL;
    }
}
