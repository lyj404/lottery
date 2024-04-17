package com.lyj.lotterapp.user.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterapp.assembler.UserAssembler;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterclient.dto.query.UserListByParamQuery;
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
public class UserListByParamQueryExe {
    private final UserGateway userGateway;

    public IPage<UserVO> execute(UserListByParamQuery query) {
        IPage<UserEntity> iPage = userGateway.listByParamQuery(query);
        return iPage.convert(UserAssembler::toUserVo);
    }
}
