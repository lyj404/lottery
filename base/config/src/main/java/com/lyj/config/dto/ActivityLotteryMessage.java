package com.lyj.config.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lyj
 * @date 2023-07-11
 **/
@Data
@Accessors(chain = true)
public class ActivityLotteryMessage {
    /**
     * 业务唯一id
     */
    private Long id;
    private String uuid;

    /**
     * JSON内容对象
     */
    private String body;
}
