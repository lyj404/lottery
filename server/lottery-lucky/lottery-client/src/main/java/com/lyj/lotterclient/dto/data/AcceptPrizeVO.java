package com.lyj.lotterclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-13
 **/
@Data
public class AcceptPrizeVO {
    private Long id;

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
}
