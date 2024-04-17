package com.lyj.lotterclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.UserRegisterCmd;
import com.lyj.lotterclient.dto.UserUpdateCmd;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterclient.dto.query.UserListByParamQuery;
import com.lyj.lotterclient.dto.query.UserLoginQuery;

/**
 * @author lyj
 * @date 2023-06-30
 **/
public interface IUserService {
    /**
     * 用户注册
     *
     * @param cmd UserRegisterCmd
     * @return UserVO
     */
    UserVO register(UserRegisterCmd cmd);

    /**
     * 用户登录
     *
     * @param query UserLoginQuery
     * @return String
     */
    String login(UserLoginQuery query);

    /**
     * 分页查询
     *
     * @param query UserListByParamQuery
     * @return IPage<UserVO>
     */
    IPage<UserVO> page(UserListByParamQuery query);

    /**
     * 获取用户信息
     *
     * @param id Long
     * @return UserVO
     */
    UserVO one(Long id);

    /**
     * 用户修改
     *
     * @param cmd UserUpdateCmd
     * @return UserVO
     */
    UserVO update(UserUpdateCmd cmd);
}
