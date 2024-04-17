package com.lyj.lotterdomain.records;

import com.lyj.common.enums.RecordStatusEnum;
import com.lyj.config.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lyj
 * @date 2023-07-11
 **/
@Slf4j
public class RecordStatus {
    /**
     * 状态，0，1，2，3
     */
    private RecordStatusEnum status;

    public Integer getState() {
        return this.status.getValue();
    }

    public RecordStatus(Integer status) {
        AssertUtil.isTrue(status < 0, "记录状态无效");
        if (RecordStatusEnum.STATUS_0.getValue().equals(status)) {
            this.status = RecordStatusEnum.STATUS_0;
            return;
        }
        if (RecordStatusEnum.STATUS_1.getValue().equals(status)) {
            this.status = RecordStatusEnum.STATUS_1;
            return;
        }
        if (RecordStatusEnum.STATUS_2.getValue().equals(status)) {
            this.status = RecordStatusEnum.STATUS_2;
            return;
        }
        if (RecordStatusEnum.STATUS_3.getValue().equals(status)) {
            this.status = RecordStatusEnum.STATUS_3;
            return;
        }
        if (RecordStatusEnum.STATUS_4.getValue().equals(status)) {
            this.status = RecordStatusEnum.STATUS_4;
            return;
        }
        AssertUtil.isTrue(true, "记录状态无效");
    }
}
