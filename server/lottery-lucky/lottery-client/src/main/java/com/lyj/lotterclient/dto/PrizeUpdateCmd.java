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
public class PrizeUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull(message = "id不为空")
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
}
