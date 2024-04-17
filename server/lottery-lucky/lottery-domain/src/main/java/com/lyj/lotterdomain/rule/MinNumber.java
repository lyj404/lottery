package com.lyj.lotterdomain.rule;

import com.lyj.config.exception.BaseException;
import lombok.Getter;

/**
 * @author lyj
 * @date 2023-07-05
 **/
@Getter
public class MinNumber {
    private Integer number;

    public MinNumber(Integer number) {
        if (number < 1) {
            throw new BaseException("数量必须大于等于1");
        }
        this.number = number;
    }
}
