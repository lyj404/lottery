package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityRuleAddCmd extends Command {
    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 规则id
     */
    private Long ruleId;
}
