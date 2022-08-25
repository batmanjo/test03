package com.w.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yanmiao.wu
 * @create 2022-08-25 10:55
 */
@Component
@Order(-1)
public class AlphaFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("测试一下全局过滤器");
        System.out.println(exchange.getRequest().toString());
        return chain.filter(exchange);
    }
}
