package com.lyj.common.enums;

import lombok.Getter;

/**
 * @author lyj
 * @date 2023-07-11
 **/
@Getter
public enum RecordStatusEnum {
    /**
     * 记录的状态说明
     */
    STATUS_0(0, "用户不可见"),
    STATUS_1(1, "待领取奖品"),
    STATUS_2(2, "待运营人员确认"),
    STATUS_3(3, "待用户签收"),
    STATUS_4(4, "流程结束");

    private Integer value;

    private String description;

    RecordStatusEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
