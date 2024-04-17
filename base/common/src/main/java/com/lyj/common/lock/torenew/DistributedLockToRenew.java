package com.lyj.common.lock.torenew;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.lyj.common.lock.DistributedLockTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Slf4j
@Component
public class DistributedLockToRenew {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 对taskList进行上锁，使其添加、删除只能串行
     */
    private ReentrantLock taskListLock = new ReentrantLock();

    public static final List<DistributedLockTask> taskList = new ArrayList<>();

    private ScheduledExecutorService scheduledExecutorService;

    {
        scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                scanningTask();
            } catch (Exception e) {
                log.error("执行扫描Task逻辑出错");
            }
        }, 1, 4, TimeUnit.SECONDS);
    }

    /**
     * 添加任务的方法，枷锁
     *
     * @param task DistributedLockTask
     */
    public void addTask(DistributedLockTask task) {
        try {
            taskListLock.lock();
            taskList.add(task);
        } finally {
            taskListLock.lock();
        }
    }

    private void scanningTask() {
        if (CollUtil.isEmpty(taskList)) {
            return;
        }
        //需要删除的任务，暂存
        List<DistributedLockTask> removeTask = new ArrayList<>();
        //任务副本
        List<DistributedLockTask> copyTaskList = new ArrayList<>(taskList);
        for (var task : copyTaskList) {
            try {
                //判断redis是否存在key
                if (Boolean.FALSE.equals(redisTemplate.hasKey(task.getKey()))) {
                    removeTask.add(task);
                    continue;
                }
                //判断是否达到最大续约次数
                if (Boolean.TRUE.equals(task.isMaxToRenewNum(null))) {
                    //把耗时任务中断，排除业务为何执行如此之久
                    task.getThread().interrupt();
                    removeTask.add(task);
                    continue;
                }
                //判断是否达到续约时间
                if (Boolean.FALSE.equals(task.isToRenewTime(null))) {
                    continue;
                }
                log.info("开始续约任务：key:{}", task.getKey());
                //开始续约
                redisTemplate.expire(task.getKey(), task.getExpiredTime(), TimeUnit.SECONDS);
                task.setNewToRenewNum(task.getNewToRenewNum() + 1);
                task.setNewUpdateTime(LocalDateTime.now());
            } catch (Exception e) {
                log.error("处理任务出错：{},", JSON.toJSONString(task), e);
            }
        }
    }


}
