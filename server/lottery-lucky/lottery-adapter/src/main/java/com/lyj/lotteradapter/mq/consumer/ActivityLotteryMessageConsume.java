package com.lyj.lotteradapter.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyj.config.dto.ActivityLotteryMessage;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterapp.activity.command.RedisReduceAwardNumberLotteryExe;
import com.lyj.lotterapp.context.ActivityLotteryContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-11
 **/
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "activity-lottery-topic", consumerGroup = "lottery")
public class ActivityLotteryMessageConsume implements RocketMQListener<ActivityLotteryMessage> {
    private final RedisReduceAwardNumberLotteryExe lotteryExe;

    @Override
    public void onMessage(ActivityLotteryMessage activityLotteryMessage) {
        log.info("接收到MQ消息了, {}", JSON.toJSONString(activityLotteryMessage));
        String body = activityLotteryMessage.getBody();
        ActivityLotteryContext context = JSON.parseObject(body, ActivityLotteryContext.class);
        //扣减库存：MySQL
        if (Boolean.FALSE.equals(lotteryExe.mqReduceOfInventoryAndUpdateRecordStatus(context))) {
            AssertUtil.isTrue(true, "执行消费MQ逻辑失败（扣减库存核修改不可见记录）");
        }
    }
}
