package com.xu.service;

import com.xu.pojo.Resume;

/**
 * @Author xushiwei
 * @Date 2022/11/8 15:37
 * @Version 1.0
 */
public interface ResumeService {
    Resume findDefaultResumeByUserId(Long userId);
}
