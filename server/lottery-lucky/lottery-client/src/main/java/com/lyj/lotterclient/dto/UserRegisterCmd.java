package com.lyj.lotterclient.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyj
 * @date 2023-06-30
 **/
@Data
public class UserRegisterCmd extends Command {
    /**
     * 账号
     */
    @NotNull(message = "账号不为空")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不为空")
    private String password;

    /**
     * 名字
     */
    @NotNull(message = "名字不为空")
    private String name;

    /**
     * 电话
     */
    @NotNull(message = "电话不为空")
    private String phone;
}
