package com.lyj.common.annotation;

import java.lang.annotation.*;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DistributedLock {
    String key() default "distributedLock";

    /**
     * 加锁失败，是否抛错，默认 false，不抛错
     *
     * @return boolean
     */
    boolean lockFail() default false;

    /**
     * 加锁失败，抛错的提示信息
     *
     * @return String
     */
    String failMessage() default "";

    /**
     * 过期时间，单位：秒
     */
    long expiredTime() default 30L;


    /**
     * 最大续约次数
     */
    int maxToRenewNum() default 30;
}