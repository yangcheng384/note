package com.misc.note.gateway.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misc.note.common.exception.BizException;
import com.misc.note.common.resp.ResultEnum;
import com.misc.note.common.resp.ResultUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class NoteServerAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add("Content-Type", "application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        DataBuffer buffer;
        try {
            buffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(ResultUtil.resp(ResultEnum.FORBIDDEN)));
        } catch (JsonProcessingException e) {
            throw new BizException(ResultEnum.FORBIDDEN);
        }
        return response.writeWith(Mono.just(buffer));
    }
}
