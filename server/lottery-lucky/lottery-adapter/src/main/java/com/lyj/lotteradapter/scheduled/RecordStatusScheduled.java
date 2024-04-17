package com.lyj.lotteradapter.scheduled;

import com.lyj.common.annotation.DistributedLock;
import com.lyj.lotterapp.activity.command.RedisReduceAwardNumberLotteryExe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-11
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RecordStatusScheduled {
    private final RedisReduceAwardNumberLotteryExe lotteryExe;
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 每隔五分钟执行一次
     * 定时扫描用户不可见的中将记录，然后对比当前时间和数据创建时间
     * 发现两者相隔10分钟，那么定时任务就可以把这个记录查询出来，在执行一次，
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    @DistributedLock(key = "reduceOfInventoryAndUpdateRecordStatus")
    public void reduceOfInventoryAndUpdateRecordStatus() {
        lotteryExe.scheduleExecute();
    }
}
