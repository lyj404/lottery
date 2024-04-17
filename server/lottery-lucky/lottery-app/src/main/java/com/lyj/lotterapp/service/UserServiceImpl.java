package com.lyj.lotterapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.config.exception.BaseException;
import com.lyj.config.utils.JwtUtil;
import com.lyj.lotterapp.user.command.UserRegisterCmdExe;
import com.lyj.lotterapp.user.command.UserUpdateCmdExe;
import com.lyj.lotterapp.user.query.UserListByParamQueryExe;
import com.lyj.lotterapp.user.query.UserLoginQueryExe;
import com.lyj.lotterclient.api.IUserService;
import com.lyj.lotterclient.dto.UserRegisterCmd;
import com.lyj.lotterclient.dto.UserUpdateCmd;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterclient.dto.query.UserListByParamQuery;
import com.lyj.lotterclient.dto.query.UserLoginQuery;
import com.lyj.lotterclient.feign.WalletFeignApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author lyj
 * @date 2023-07-01
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRegisterCmdExe userRegisterCmdExe;
    private final UserLoginQueryExe userLoginQueryExe;
    private final UserListByParamQueryExe userListByParamQueryExe;
    private final UserUpdateCmdExe userUpdateCmdExe;
    private final WalletFeignApi walletFeignApi;

    /**
     * 用户注册
     *
     * @param cmd UserRegisterCmd
     * @return UserVO
     */
    @Override
    public UserVO register(UserRegisterCmd cmd) {
        UserVO userVO = userRegisterCmdExe.execute(cmd);
        try {
            walletFeignApi.initUserWallet(userVO.getId());
        } catch (Exception e) {
            log.error("用户注册成功，但是初始化钱包失败", e);
        }
        return userVO;
    }

    /**
     * 用户登录
     *
     * @param query UserLoginQuery
     * @return UserVO
     */
    @Override
    public String login(UserLoginQuery query) {
        UserVO userVO = userLoginQueryExe.execute(query);
        return JwtUtil.createToken(Map.of(
                "username", userVO.getUsername(),
                "name", userVO.getName(),
                "phone", userVO.getPhone(),
                "userId", userVO.getId()
        ));
    }

    /**
     * 分页查询
     *
     * @param query UserListByParamQuery
     * @return IPage<UserVO>
     */
    @Override
    public IPage<UserVO> page(UserListByParamQuery query) {
        return userListByParamQueryExe.execute(query);
    }

    /**
     * 获取用户信息
     *
     * @param id Long
     * @return UserVO
     */
    @Override
    public UserVO one(Long id) {
        final var query = new UserListByParamQuery();
        query.setId(id);
        IPage<UserVO> page = userListByParamQueryExe.execute(query);
        if (CollectionUtils.isEmpty(page.getRecords())) {
            throw new BaseException("该用户不存在");
        }
        return page.getRecords().get(0);
    }

    /**
     * 用户修改
     *
     * @param cmd UserUpdateCmd
     * @return UserVO
     */
    @Override
    public UserVO update(UserUpdateCmd cmd) {
        return userUpdateCmdExe.execute(cmd);
    }
}
