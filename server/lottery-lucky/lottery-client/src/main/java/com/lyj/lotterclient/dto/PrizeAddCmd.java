package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Data
public class PrizeAddCmd extends Command {
    /**
     * 奖品名称
     */
    @NotNull(message = "奖品名称不为空")
    private String prizeName;

    /**
     * 库存
     */
    @NotNull(message = "库存不为空")
    private Integer inventory;

    /**
     * 金额
     */
    @NotNull(message = "金额不为空")
    private BigDecimal money;

    /**
     * 类型，1-商品，2-金钱
     */
    @NotNull(message = "类型不为空")
    private Integer type;
}
