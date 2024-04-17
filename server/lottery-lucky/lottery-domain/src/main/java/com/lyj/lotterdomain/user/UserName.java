package com.lyj.lotterdomain.user;

import com.lyj.config.exception.BaseException;
import lombok.Getter;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-06-30
 **/
@Getter
public class UserName {
    /**
     * 账号
     */
    private String username;

    public UserName(String username) {
        if (Objects.isNull(username)) {
            throw new BaseException("账号不能为空");
        }
        this.username = username;
    }
}
