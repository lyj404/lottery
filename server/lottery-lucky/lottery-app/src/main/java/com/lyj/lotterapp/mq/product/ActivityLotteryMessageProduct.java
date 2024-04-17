package com.lyj.lotterapp.mq.product;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.lyj.config.dto.ActivityLotteryMessage;
import com.lyj.lotterapp.context.ActivityLotteryContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-11
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ActivityLotteryMessageProduct {
    private final RocketMQTemplate rocketMQTemplate;

    public Boolean send(ActivityLotteryContext context) {
        final var activityLotterMessage = new ActivityLotteryMessage()
                .setUuid(IdUtil.simpleUUID())
                .setBody(JSON.toJSONString(context));

        Message<ActivityLotteryMessage> message = MessageBuilder
                .withPayload(activityLotterMessage)
                .build();

        SendResult sendResult = rocketMQTemplate.syncSend("activity-lottery-topic", message);

        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            log.info("消息发送成功，业务ID：{}, uuid: {}",
                    activityLotterMessage.getId(), activityLotterMessage.getUuid());
            return Boolean.TRUE;
        }
        log.info("消息发送失败");
        return Boolean.FALSE;
    }
}
