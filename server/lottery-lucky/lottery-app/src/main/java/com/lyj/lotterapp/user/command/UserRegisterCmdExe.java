package com.lyj.lotterapp.user.command;

import com.lyj.config.exception.BaseException;
import com.lyj.lotterapp.assembler.UserAssembler;
import com.lyj.lotterclient.dto.UserRegisterCmd;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterdomain.gateway.UserGateway;
import com.lyj.lotterdomain.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-01
 **/
@Component
@RequiredArgsConstructor
public class UserRegisterCmdExe {
    private final UserGateway userGateway;

    public UserVO execute(UserRegisterCmd cmd) {
        UserEntity oldEntity = userGateway.findByUserName(null, cmd.getUsername());
        if (Objects.nonNull(oldEntity)) {
            throw new BaseException("账号已存在");
        }
        UserEntity userEntity = userGateway.save(UserAssembler.toAddEntity(cmd));
        return UserAssembler.toUserVo(userEntity);
    }
}
