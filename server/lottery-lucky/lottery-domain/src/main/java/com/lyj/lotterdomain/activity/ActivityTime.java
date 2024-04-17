package com.lyj.lotterdomain.activity;

import com.lyj.config.exception.BaseException;
import com.lyj.config.exception.CodeException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Getter
public class ActivityTime {
    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    public ActivityTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
            throw new BaseException("活动时间不为空");
        }
        if (startTime.isAfter(endTime)) {
            throw new CodeException(50050, "活动时间非法");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 判断活动状态
     *
     * @return ActivityStatusEnum
     */
    public ActivityStatusEnum getStatus() {
        LocalDateTime now = LocalDateTime.now();
        return getStatus(now);
    }

    public ActivityStatusEnum getStatus(LocalDateTime time) {
        if (time.isBefore(this.startTime)) {
            return ActivityStatusEnum.NOT_START;
        }
        if (time.isAfter(this.startTime) && time.isBefore(this.endTime)) {
            return ActivityStatusEnum.START;
        }
        return ActivityStatusEnum.END;
    }
}
