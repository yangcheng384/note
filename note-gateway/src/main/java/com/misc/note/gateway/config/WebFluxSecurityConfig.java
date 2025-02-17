package com.misc.note.gateway.config;

import com.google.common.collect.Lists;
import com.misc.note.gateway.components.*;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

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
        http
            .authorizeExchange(exchange ->
                    exchange.pathMatchers(Lists.newArrayList(ignoreUrls.getUrls()).toArray(String[]::new)).permitAll()
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
