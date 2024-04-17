package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import java.util.Date;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityRuleUpdateCmd extends Command {
    /**
     * 主键
     */
    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
