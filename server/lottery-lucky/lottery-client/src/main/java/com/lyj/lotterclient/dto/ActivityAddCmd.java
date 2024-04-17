package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Data
public class ActivityAddCmd extends Command {
    /**
     * 活动名称
     */
    @NotNull(message = "活动名称不为空")
    private String activityName;

    /**
     * 活动开始时间
     */
    @NotNull(message = "活动开始时间不为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    @NotNull(message = "活动结束时间不为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 活动描述
     */
    @NotNull(message = "活动描述不为空")
    private String activityDescription;
}
