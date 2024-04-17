package com.lyj.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.lyj.config.exception.BaseException;
import com.lyj.config.utils.JwtUtil;
import com.lyj.config.vo.FailResult;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Component
@Order(0)
@Data
@ConfigurationProperties(prefix = "lottery.gateway")
public class JwtTokenGlobalFilter implements GlobalFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenGlobalFilter.class);
    /**
     * 忽略认证url
     */
    private Set<String> ignoreUrlSet = Set.of(
            "/v1/user/login",
            "/v1/user/register"
    );
    /**
     * 认证标识
     */
    private String authorization = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求url
        String url = exchange.getRequest().getURI().getPath();
        for (var ignoreUrl : ignoreUrlSet) {
            if (ignore(url, ignoreUrl)) {
                return chain.filter(exchange);
            }
        }
        //认证
        String token = exchange.getRequest().getHeaders().getFirst(this.authorization);
        ServerHttpResponse response = exchange.getResponse();
        try {
            Map<String, Object> userMap = JwtUtil.verifyToken(token);
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            builder.header("username", URLEncoder.encode(userMap.get("username").toString(), StandardCharsets.UTF_8));
            builder.header("name", URLEncoder.encode(userMap.get("name").toString(), StandardCharsets.UTF_8));
            builder.header("phone", Objects.isNull(userMap.get("phone").toString()) ? "" : userMap.get("phone").toString());
            builder.header("userId", Objects.isNull(userMap.get("userId")) ? "0" : userMap.get("userId").toString());
            return chain.filter(exchange);
        } catch (Exception e) {
            LOGGER.error("token认证失败:{}", e.getMessage());
            return errorInfo(response, "认证出错，请重新登录");
        }
    }

    private Mono<Void> errorInfo(ServerHttpResponse response, String message) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        String str = JSON.toJSONString(new FailResult(500, message));
        DataBuffer buffer = response.bufferFactory().wrap(str.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    /**
     * 忽略请求
     *
     * @param url       请求url
     * @param ignoreUrl 需要忽略的url
     * @return Boolean
     */
    private Boolean ignore(String url, String ignoreUrl) {
        if (StringUtils.isBlank(url)) {
            throw new BaseException("请求地址有误");
        }
        return Pattern.matches(ignoreUrl, url);
    }
}
