package com.xu.controller;

import com.xu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xushiwei
 * @Date 2022/11/8 15:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private ResumeService resumeService;

//    @GetMapping("/openstate/{userId}")
//    public Integer findDefaultByUserId(@PathVariable Long userId){
//        return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
//    }

    @GetMapping("/openstate/{userId}")
    public Integer findDefaultByUserId(@PathVariable Long userId){
        return port;
    }
}
