package com.lyj.lotterapp.listener;

import com.lyj.lotterapp.assembler.AwardAssembler;
import com.lyj.lotterapp.listener.event.ActivityCreateEvent;
import com.lyj.lotterclient.dto.data.ActivityConfigVO;
import com.lyj.lotterdomain.award.AwardEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-10
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class AwardInventoryToRedisApplicationListener implements ApplicationListener<ActivityCreateEvent> {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 奖项库存的key
     */
    private static final String awardInventoryKey = "lottery:activity:award:";


    @Override
    public void onApplicationEvent(ActivityCreateEvent event) {
        log.info("开始将奖品库存存入redis");
        ActivityConfigVO activityConfig = event.getActivityConfig();
        for (var awardVO : activityConfig.getAwardVOList()) {
            AwardEntity awardEntity = AwardAssembler.toAwardEntity(awardVO);
            if (!awardEntity.isPrize()) {
                return;
            }
            redisTemplate.opsForValue().set(getKey(activityConfig.getActivityVO().getId(), awardVO.getId()), awardVO.getNumber());
        }
        log.info("奖品库存已存入redis");
    }

    public static String getKey(Long activityId, Long awardId) {
        return awardInventoryKey + activityId + ":" + awardId;
    }
}
