package com.lyj.lotterdomain.acceptprize;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Data
public class AcceptPrizeEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 抽奖记录id
     */
    private Long recordId;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
