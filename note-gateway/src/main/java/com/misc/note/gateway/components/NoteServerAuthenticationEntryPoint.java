package com.misc.note.gateway.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misc.note.common.exception.BizException;
import com.misc.note.common.resp.ResultEnum;
import com.misc.note.common.resp.ResultUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class NoteServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        DataBuffer buffer;
        try {
            buffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(ResultUtil.resp(ResultEnum.UNAUTHORIZED)));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        return response.writeWith(Mono.just(buffer));
    }
}
