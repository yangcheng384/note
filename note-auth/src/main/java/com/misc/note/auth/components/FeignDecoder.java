package com.misc.note.auth.components;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misc.note.common.resp.ResultVO;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@Slf4j
@AllArgsConstructor
public class FeignDecoder implements Decoder {

    private final ObjectMapper objectMapper;

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
        JavaType resultType = objectMapper.getTypeFactory().constructType(type);
        return objectMapper.readValue(body, resultType);
    }
}
