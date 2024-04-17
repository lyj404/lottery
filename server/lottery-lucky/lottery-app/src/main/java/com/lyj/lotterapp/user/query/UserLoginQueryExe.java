package com.lyj.lotterapp.user.query;

import com.lyj.config.exception.BaseException;
import com.lyj.lotterapp.assembler.UserAssembler;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterclient.dto.query.UserLoginQuery;
import com.lyj.lotterdomain.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-03
 **/
@Component
@RequiredArgsConstructor
public class UserLoginQueryExe {
    private final UserGateway userGateway;

    public UserVO execute(UserLoginQuery query) {
        //根据账号查询用户
        final var userEntity = userGateway.findByUserName(null, query.getUsername());

        //用户不存在
        if (Objects.isNull(userEntity)) {
            throw new BaseException("登录失败，用户不存");
        }

        //判断用户密码
        if (!userEntity.getPassword().isEqual(query.getPassword())) {
            throw new BaseException("登录失败，密码错误");
        }
        return UserAssembler.toUserVo(userEntity);
    }
}
