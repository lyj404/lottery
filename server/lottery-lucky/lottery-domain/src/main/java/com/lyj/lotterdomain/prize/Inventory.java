package com.lyj.lotterdomain.prize;

import com.lyj.config.exception.BaseException;
import lombok.Getter;

/**
 * @author lyj
 * @date 2023-07-04
 **/
@Getter
public class Inventory {
    /**
     * 库存 = 0，表示非奖品，例如谢谢参与
     */
    private Integer inventory;

    public Inventory(Integer inventory) {
        if (inventory < 0) {
            throw new BaseException("库存数量不能小于零");
        }
        this.inventory = inventory;
    }
}
