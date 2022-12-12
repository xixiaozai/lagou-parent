package com.xu.service;

import org.springframework.stereotype.Component;

/**
 * 降级回退逻辑需要定义一个类  实现feign客户端接口  重新实现方法 */
@Component
public class ResumeFallback implements ResumeServiceFeignClient {
    @Override
    public Integer findDefaultByUserId(Long userId) {
        return 1;
    }
}
