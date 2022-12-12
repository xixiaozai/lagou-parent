package com.xu;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author xushiwei
 * @Date 2022/11/8 16:03
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient //开启服务发现
//@EnableCircuitBreaker //开启熔断机制
//@EnableFeignClients // 开启feigns
public class AutoDeliverApplication8090 {
    public static void main(String[] args) {
        SpringApplication.run(AutoDeliverApplication8090.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 注册监听servlet  用于监听hystrim.stream
     * 在被监控的微服务中注册servlet 后期通过访问这个接口获取hystrix监控数据
     * 前提: 被监控的微服务需要引入springboot的acurator
    * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
