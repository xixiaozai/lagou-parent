package com.xu.controller;


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


    @GetMapping("/openstate/{userId}")
    public Integer findDefaultByUserId(@PathVariable Long userId){
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("current  is " + port);
        return port;
    }
}
