package com.lyj.lotterclient.dto.data;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Data
public class PrizeVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 类型，1-商品，2-金钱
     */
    private Integer type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
