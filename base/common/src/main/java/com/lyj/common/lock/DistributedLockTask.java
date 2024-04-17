package com.lyj.common.lock;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Data
public class DistributedLockTask {
    private String key;

    /**
     * 过期时间，单位：秒
     */
    private Long expiredTime;

    /**
     * 最大续约次数
     */
    private Integer maxToRenewNum;

    /**
     * 当前续约次数
     */
    private Integer newToRenewNum;

    /**
     * 最新更新时间
     */
    private LocalDateTime newUpdateTime;

    /**
     * 业务线程
     */
    private Thread thread;

    /**
     * 是否达到续约时间
     *
     * @param time LocalDateTime
     * @return Boolean
     */
    public Boolean isToRenewTime(LocalDateTime time) {
        if (Objects.isNull(time)) {
            time = this.newUpdateTime;
        }
        LocalDateTime nowTime = LocalDateTime.now();
        Duration duration = Duration.between(nowTime, time);

        return duration.toSeconds() >= (double) ((this.expiredTime / 3) * 2);
    }

    /**
     * 是否达到最大续约次数
     *
     * @param num Integer
     * @return Boolean
     */
    public Boolean isMaxToRenewNum(Integer num) {
        if (Objects.isNull(num)) {
            num = this.maxToRenewNum;
        }

        return num >= this.maxToRenewNum;
    }
}
