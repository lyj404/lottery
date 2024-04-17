package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull(message = "id不为空")
    private Long id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 活动描述
     */
    private String activityDescription;
}
