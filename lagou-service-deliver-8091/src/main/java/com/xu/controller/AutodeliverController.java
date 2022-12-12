package com.xu.controller;

import com.xu.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xushiwei
 * feign 调用
 * @Date 2022/11/8 16:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenstate(@PathVariable Long userId){
        Integer defaultByUserId = resumeServiceFeignClient.findDefaultByUserId(userId);
        return defaultByUserId;
    }
}
