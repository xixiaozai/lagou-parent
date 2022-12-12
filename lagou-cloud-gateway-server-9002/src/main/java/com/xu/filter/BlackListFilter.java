package com.xu.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局过滤器  实现白名单ip限制
 */
@Slf4j
//@Component  // 容器扫描到就注册了
public class BlackListFilter implements GlobalFilter, Ordered {

    static List<String> blackList = new ArrayList<>();

    static {
        blackList.add("127.0.0.1");
        blackList.add("0:0:0:0:0:0:0:1");// 模拟本机地址
    }
    /**
     * 过滤器全局方法
     * @param exchange 封装了上下文
     * @param chain 网关过滤器链  包含全局与单路由过滤器链
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取访问ip 判断是否在黑名单内
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String hostString = request.getRemoteAddress().getHostString();
        if(blackList.contains(hostString)){
            // 决绝访问 返回
            log.info("ip 在黑名单 拒绝访问");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String data = "拒绝访问";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
            return response.writeWith(Mono.just(wrap));
        }
        // 合法请求
        return chain.filter(exchange);
    }

    /**
     * 返回值代表优先级， 数值越小 优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
