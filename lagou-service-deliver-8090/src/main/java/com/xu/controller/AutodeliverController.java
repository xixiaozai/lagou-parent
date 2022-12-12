package com.xu.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @Author xushiwei
 * @Date 2022/11/8 16:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    private RestTemplate restTemplate;



    @Autowired
    private DiscoveryClient discoveryClient;

//    @GetMapping("/checkState/{userId}")
//    public Integer findResumeOpenstate(@PathVariable Long userId){
//        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/openstate/" + userId, Integer.class);
//        System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + forObject);
//        return forObject;
//    }

    /**
     * 使用restTemplate
     * @param userId
     * @return
     */
//    @GetMapping("/checkState/{userId}")
//    public Integer findResumeOpenstate(@PathVariable Long userId){
//        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-resume");
//        ServiceInstance serviceInstance = instances.get(0);
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//        Integer forObject = restTemplate.getForObject("http://"+host+":"+port+"/resume/openstate/" + userId, Integer.class);
//        // 调用提供者
//        System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + forObject);
//        return forObject;
//    }

    /**
     * 使用ribbon做负载
     * @param userId
     * @return
     */
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenstate(@PathVariable Long userId){
        Integer forObject = restTemplate.getForObject("http://lagou-service-resume/resume/openstate/" + userId, Integer.class);
        // 调用提供者
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + forObject);
        return forObject;
    }


    /**
     * 提供者模拟超时，调用方法添加hystrix控制
     * 使用HystrixCommand注解进行熔断 没有fallback
     * @param userId
     * @return
     */
    @HystrixCommand(threadPoolKey = "findResumeOpenStateTimeout",  //key要区分开 不然就公用了
            threadPoolProperties = { // 线程池细节配置
                @HystrixProperty(name="coreSize", value="1"), // 线程数配置
                @HystrixProperty(name="maxQueueSize", value="20"),// 等待队列长度
            },
            commandProperties = {// 熔断的一些细节属性配置
                @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000")// 超过2s就走这个逻辑
            }
        )
    @GetMapping("/checkStateTimeout/{userId}")
    public Integer findResumeOpenstateTimeout(@PathVariable Long userId){
        // 使⽤ribbon不需要我们⾃⼰获取服务实例然后选择⼀个那么去访问了（⾃⼰的负载均衡）
        System.out.println("timeout");
        String url = "http://lagou-service-resume/resume/openstate/" + userId; // 指定服务名
        Integer forObject = restTemplate.getForObject(url,
                Integer.class);
        System.out.println("123");
        return forObject;
    }


    @GetMapping("/checkStateTimeoutFallback/{userId}")
    @HystrixCommand(
            threadPoolKey = "findResumeOpenStateTimeoutFallback",// 不唯一的话 就是公用
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value = "2"),// 线程数
                    @HystrixProperty(name="maxQueueSize", value="20")// 队列中最大等待长度
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "8000"),// 统计窗口时间
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),//统计时间窗口内最小请求数
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),// 统计时间窗口内错误率
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),//自我修复时活动窗口长度
            },
            fallbackMethod = "myFallBack" // 回退方法
    )
    public Integer findResumeOpenStateTimeoutFallback(@PathVariable Long userId){
        // 使⽤ribbon不需要我们⾃⼰获取服务实例然后选择⼀个那么去访问了（⾃⼰的负载均衡）
        System.out.println("fallback");
        String url = "http://lagou-service-resume/resume/openstate/" + userId; // 指定服务名
        Integer forObject = restTemplate.getForObject(url,
                Integer.class);
        System.out.println("234");
        return forObject;
    }

    /**
     定义回退⽅法，返回预设默认值
     注意：该⽅法形参和返回值与原始⽅法保持⼀致
     */
    public Integer myFallBack(Long userId) {
        return -123333; // 兜底数据
    }

    /**
     * 1）服务提供者处理超时，熔断，返回错误信息
     * 2）有可能服务提供者出现异常直接抛出异常信息
     *
     * 以上信息，都会返回到消费者这⾥，很多时候消费者服务不希望把收到异常/
     错误信息再抛到它的上游去
     * ⽤户微服务 — 注册微服务 — 优惠券微服务
     * 1 登记注册
     * 2 分发优惠券（并不是核⼼步骤），这⾥如果调⽤优惠券
     微服务返回了异常信息或者是熔断后的错误信息，这些信息如果抛给⽤户很不友好
     * 此时，我们可以返回⼀个兜底数据，预设的默认
     值（服务降级）
     *
     *
     */
}
