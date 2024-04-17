package com.lyj.common.interceptor;

import com.lyj.config.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Component
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> userMap = new HashMap<>(16);
        userMap.put("username", URLDecoder.decode(StringUtils.isNotBlank(request.getHeader("username")) ?
                request.getHeader("username") : "", StandardCharsets.UTF_8));
        userMap.put("name", URLDecoder.decode(StringUtils.isNotBlank(request.getHeader("name"))
                ? request.getHeader("name") : "", StandardCharsets.UTF_8));
        userMap.put("phone", StringUtils.isNotBlank(request.getHeader("phone")) ? request.getHeader("phone") : "");
        userMap.put("userId", request.getHeader("userId"));

        SecurityUtil.addUserInfo(userMap);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        SecurityUtil.remove();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
