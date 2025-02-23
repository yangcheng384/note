package com.misc.note.gateway.components;

import com.auth0.jwt.interfaces.Claim;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.misc.note.common.constant.JwtConstant;
import com.misc.note.common.util.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class JwtSecurityContextRepository implements ServerSecurityContextRepository {

    @Resource
    private IgnoreUrls ignoreUrls;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        log.info("请求地址:{}", request.getURI());
        List<String> ignores = ignoreUrls.getUrls();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String ignore : ignores) {
            if (pathMatcher.match(ignore, request.getURI().getPath())) {
                log.info("白名单路径，放行！");
                return Mono.empty();
            }
        }
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (Strings.isNullOrEmpty(token)){
            log.error("token不存在！");
            return Mono.empty();
        }

        Map<String, Claim> claims = JwtUtil.verifyToken(token);
        String username = claims.get(JwtConstant.USERNAME).asString();
        List<String> roles = claims.get(JwtConstant.ROLE).asList(String.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(Joiner.on(",").join(roles)));
        ReactiveSecurityContextHolder.withAuthentication(authentication);
        return Mono.fromCallable(() ->
                new UsernamePasswordAuthenticationToken(username, null,
                        AuthorityUtils.createAuthorityList(roles))).map(SecurityContextImpl::new);
    }
}