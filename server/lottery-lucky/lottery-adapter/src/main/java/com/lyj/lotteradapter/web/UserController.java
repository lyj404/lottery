package com.lyj.lotteradapter.web;

import com.lyj.common.annotation.ResponseResult;
import com.lyj.config.utils.SecurityUtil;
import com.lyj.lotterclient.api.IUserService;
import com.lyj.lotterclient.dto.UserRegisterCmd;
import com.lyj.lotterclient.dto.UserUpdateCmd;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterclient.dto.query.UserLoginQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyj
 * @date 2023-07-01
 **/
@ResponseResult
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    private final IUserService userService;

    /**
     * 用户注册
     *
     * @param cmd 注册相关信息
     * @return 用户注册信息
     */
    @PostMapping("/register")
    public UserVO register(@Validated @RequestBody UserRegisterCmd cmd) {
        return userService.register(cmd);
    }

    /**
     * 用户登录
     *
     * @param query UserLoginQuery
     * @return token
     */
    @PostMapping("/login")
    public String login(@Validated @RequestBody UserLoginQuery query) {
        return userService.login(query);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/one")
    public UserVO one() {
        return userService.one(SecurityUtil.getUserId());
    }

    /**
     * 修改用户
     *
     * @param cmd 需要修改的数据
     * @return 修改之后的用户信息
     */
    @PostMapping("/update")
    public UserVO update(@Validated @RequestBody UserUpdateCmd cmd) {
        return userService.update(cmd);
    }
}
