package com.xu.ite;

import org.springframework.stereotype.Component;

/**
 * @Author xushiwei 降级回退逻辑需要定义⼀个类，实现FeignClient接⼝，实现接⼝中的⽅法
 * @Date 2022/11/14 17:32
 * @Version 1.0
 */
@Component
public class ResumeFallback implements ResumeFeignClient {
    @Override
    public Integer findResumeOpenState(Long userId) {
        return -6;
    }
}
