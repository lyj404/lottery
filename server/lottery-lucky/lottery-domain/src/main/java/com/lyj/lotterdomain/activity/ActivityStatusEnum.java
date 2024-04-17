package com.lyj.lotterdomain.activity;

import lombok.Getter;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Getter
public enum ActivityStatusEnum {
    /**
     * 未开始
     */
    NOT_START(0, "未开始"),
    /**
     * 开始
     */
    START(1, "开始"),
    /**
     * 结束
     */
    END(2, "结束");

    private Integer value;
    private String description;

    ActivityStatusEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
