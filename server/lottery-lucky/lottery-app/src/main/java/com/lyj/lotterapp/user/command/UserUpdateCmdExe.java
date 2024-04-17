package com.lyj.lotterapp.user.command;

import com.lyj.lotterapp.assembler.UserAssembler;
import com.lyj.lotterclient.dto.UserUpdateCmd;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterdomain.gateway.UserGateway;
import com.lyj.lotterdomain.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lyj
 * @date 2023-07-03
 **/
@Component
@RequiredArgsConstructor
public class UserUpdateCmdExe {
    private final UserGateway userGateway;

    public UserVO execute(UserUpdateCmd cmd) {
        UserEntity entity = UserAssembler.toUpdateEntity(cmd);
        UserEntity save = userGateway.save(entity);
        return UserAssembler.toUserVo(save);
    }
}
