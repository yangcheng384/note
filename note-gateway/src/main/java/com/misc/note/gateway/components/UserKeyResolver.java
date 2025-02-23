package com.misc.note.gateway.components;

import com.google.common.base.Strings;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(getClientIp(exchange));
    }

    private static String getClientIp(ServerWebExchange exchange) {
        String ip = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
        if (Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = exchange.getRequest().getHeaders().getFirst("Proxy-Client-IP");
        }
        if (Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = exchange.getRequest().getHeaders().getFirst("WL-Proxy-Client-IP");
        }
        if (Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        } else {
            // 如果存在多个IP（代理链情况），取第一个IP
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        return ip;
    }
}
