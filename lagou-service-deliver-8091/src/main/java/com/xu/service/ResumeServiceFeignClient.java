package com.xu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// @FeignClient 表明当前为fiegn客户端  value为服务提供者名称
// fallback为熔断后的回退接口
@FeignClient(value = "lagou-service-resume", fallback = ResumeFallback.class, path = "/resume")
//@RequestMapping("/resume")
public interface ResumeServiceFeignClient {
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultByUserId(@PathVariable(value = "userId") Long userId);
}
