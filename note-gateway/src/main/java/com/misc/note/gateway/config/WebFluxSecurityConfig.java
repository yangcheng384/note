package com.misc.note.gateway.config;

import com.google.common.collect.Lists;
import com.misc.note.gateway.components.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@EnableWebFluxSecurity
@Configuration
public class WebFluxSecurityConfig {

    @Resource
    private NoteServerAuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private NoteServerAccessDeniedHandler accessDeniedHandler;

//    @Resource
//    private NoteAuthorizationManager authorizationManager;

    @Resource
    private IgnoreUrls ignoreUrls;

    @Resource
    private JwtSecurityContextRepository jwtSecurityContextRepository;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        List<String> ignoreUrlList = Optional.ofNullable(ignoreUrls)
                .map(IgnoreUrls::getUrls)
                .orElse(Collections.emptyList());

        if (ignoreUrlList.isEmpty()) {
            log.warn("ignoreUrls is not configured or is empty.");
        }
        http
            .authorizeExchange(exchange ->
                    exchange.pathMatchers(Lists.newArrayList(ignoreUrlList).toArray(String[]::new)).permitAll()
                            .anyExchange().authenticated())
//                            .access(authorizationManager))
                .securityContextRepository(jwtSecurityContextRepository)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .exceptionHandling(exchange ->
                        exchange.authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler));
        return http. build();
    }
}
