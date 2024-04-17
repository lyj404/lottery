package com.lyj.common.aop;

import com.alibaba.fastjson.JSON;
import com.lyj.common.annotation.DistributedLock;
import com.lyj.common.lock.DistributedLockTask;
import com.lyj.common.lock.torenew.DistributedLockToRenew;
import com.lyj.config.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {
    private final RedisTemplate<String, Object> redisTemplate;
    private final DistributedLockToRenew distributedLockToRenew;

    private static final String defaultKey = "distributed:lock:aspect:";

    @Pointcut("@annotation(com.lyj.common.annotation.DistributedLock)")
    public void distributedLock() {
    }

    @Around("distributedLock()")
    public void invoke(ProceedingJoinPoint joinPoint) {
        //获取注解上的key
        String key = getKey(joinPoint);
        Boolean absent = redisTemplate.opsForValue()
                .setIfAbsent(key, Thread.currentThread().getId() + "", 5, TimeUnit.MINUTES);
        if (Boolean.FALSE.equals(absent)) {
            // 判断是否需要抛错
            DistributedLock distributedLock = getDistributedLockAnnotation(getMethod(joinPoint));
            if (Boolean.FALSE.equals(distributedLock.lockFail())) {
                return;
            }
            throw new BaseException(distributedLock.failMessage());
        }

        try {
            addTask(key, joinPoint);

            //执行业务
            joinPoint.proceed();
        } catch (Throwable e) {
            log.error("分布式锁发生错误");
        } finally {
            String value = (String) redisTemplate.opsForValue().get(key);
            if (value.equals(Thread.currentThread().getId() + "")) {
                //只删除自己线程所加的锁，防止误删其他线程的锁
                redisTemplate.delete(key);
            }
        }
    }

    private void addTask(String key, ProceedingJoinPoint joinPoint) {
        DistributedLock annotation = getDistributedLockAnnotation(getMethod(joinPoint));
        //生成任务
        DistributedLockTask task = new DistributedLockTask();
        task.setKey(key);
        task.setExpiredTime(annotation.expiredTime());
        task.setMaxToRenewNum(annotation.maxToRenewNum());
        task.setNewToRenewNum(0);
        task.setNewUpdateTime(LocalDateTime.now());
        task.setThread(Thread.currentThread());
        distributedLockToRenew.addTask(task);
        log.info("task集合添加任务成功：task:{}", JSON.toJSONString(task));
    }

    private String getKey(ProceedingJoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        if (Objects.isNull(method)) {
            return defaultKey + "default-key";
        }
        DistributedLock annotation = getDistributedLockAnnotation(method);
        if (Objects.isNull(annotation)) {
            return defaultKey + "default-key";
        }

        String key = annotation.key();
        String keyPrefix = defaultKey + method.getClass().getName() + ":" + method.getName();
        if (StringUtils.isBlank(key)) {
            return keyPrefix;
        }
        // 填充 key 存在占位符的情况，并返回
        return keyPrefix + ":" + replenishKey(key, joinPoint.getArgs());
    }

    private String replenishKey(String key, Object[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            return key;
        }

        for (int i = 0; i < args.length; i++) {
            key = key.replace(String.format("{%s}", i + 1), args[i].toString());
        }
        return key;
    }


    private DistributedLock getDistributedLockAnnotation(Method method) {
        DistributedLock annotation = method.getAnnotation(DistributedLock.class);
        if (Objects.isNull(annotation)) {
            return null;
        }
        return annotation;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Method method = null;
        try {
            method = joinPoint.getTarget()
                    .getClass()
                    .getMethod(
                            joinPoint.getSignature().getName(),
                            ((MethodSignature) joinPoint.getSignature()).getParameterTypes()
                    );
        } catch (NoSuchMethodException e) {
            log.error("获取method失败");
            return null;
        }
        return method;
    }
}
