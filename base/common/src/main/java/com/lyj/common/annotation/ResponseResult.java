package com.lyj.common.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @author lyj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@RestController
public @interface ResponseResult {
    /**
     * 是否忽略
     * @return boolean
     */
    boolean ignore() default false;
}