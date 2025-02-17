package com.misc.note.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.misc.note.common.annotations.Error;
import com.misc.note.common.exception.BizException;
import com.misc.note.common.resp.ResultEnum;
import com.misc.note.common.resp.ResultUtil;
import com.misc.note.common.resp.ResultVO;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return objectMapper.writeValueAsString(ResultUtil.success(body));
        }
        if (body instanceof ResultVO) {
            return body;
        }
        return ResultUtil.success(body);
    }

    @ExceptionHandler(BizException.class)
    public ResultVO<String> handle(BizException e){
        log.error("捕获到异常：{}", e.getMessage(), e);
        return ResultUtil.resp(e.getCode(), e.getMsg(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> handle(MethodArgumentNotValidException e) throws NoSuchFieldException {
        log.error("捕获到异常：{}", e.getMessage(), e);
        Class<?> entityClass = e.getParameter().getParameterType();
        FieldError fieldError = e.getFieldError();
        Field field = entityClass.getDeclaredField(fieldError.getField());
        if (field.isAnnotationPresent(Error.class)){
            Error error = field.getAnnotation(Error.class);
            return ResultUtil.resp(error.resultEnum());
        }
        return ResultUtil.failure();
    }
}
