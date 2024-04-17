package com.lyj.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyj.common.annotation.ResponseResult;
import com.lyj.config.vo.SuccessResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-06-29
 * @SneakyThrows的作用：由lombok封装的,为代码生成一个try…catch块,并把异常向上抛出来
 **/
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        final var method = methodParameter.getMethod();
        final var clazz = Objects.requireNonNull(method, "method is null").getDeclaringClass();

        // 只处理 ResponseResult 标注的类或方法
        var annotation = clazz.getAnnotation(ResponseResult.class);
        if (Objects.isNull(annotation)) {
            annotation = method.getAnnotation(ResponseResult.class);
        }

        //如果是FileSystemResource 则不拦截
        if (method.getAnnotatedReturnType().getType().getTypeName()
                .equals(FileSystemResource.class.getTypeName())) {
            return false;
        }
        return annotation != null && !annotation.ignore();
    }


    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //处理结果集
        var successResult = SuccessResult.builder()
                .data(data)
                .build();
        if ((data instanceof String) && !MediaType.APPLICATION_XML_VALUE.equals(mediaType.toString())) {
            ObjectMapper om = new ObjectMapper();
            response.getHeaders().set("Content-Type", "application/json");
            return om.writeValueAsString(successResult);
        }

        if (Objects.isNull(data) && MediaType.TEXT_HTML_VALUE.equals(mediaType.toString())) {
            ObjectMapper om = new ObjectMapper();
            response.getHeaders().set("Content-Type", "application/json");
            return om.writeValueAsString(successResult);
        }

        return successResult;
    }
}