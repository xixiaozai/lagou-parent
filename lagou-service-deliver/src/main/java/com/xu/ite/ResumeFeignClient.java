package com.xu.ite;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author xushiwei
 * @Date 2022/11/14 16:39
 * @Version 1.0
 */

// name：调用的服务名称，和服务提供者yml中设置的spring.application.name一样
@FeignClient(name = "lagou-service-resume", fallback = ResumeFallback.class, path = "/resume")
public interface ResumeFeignClient {

    // 调用的请求路径
    @RequestMapping(value = "/resume/openstate/{userId}", method = RequestMethod.GET)
    public Integer findResumeOpenState(@PathVariable(value = "userId") Long userId);
}