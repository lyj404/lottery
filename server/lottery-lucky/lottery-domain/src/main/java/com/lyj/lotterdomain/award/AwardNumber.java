package com.lyj.lotterdomain.award;

import com.lyj.config.exception.BaseException;
import lombok.Getter;

/**
 * @author lyj
 * @date 2023-07-06
 **/
@Getter
public class AwardNumber {
    private Integer number;

    public AwardNumber(Integer number) {
        if (number < 0) {
            throw new BaseException("奖项数量不合法，必须大于等于零");
        }
        this.number = number;
    }
}
