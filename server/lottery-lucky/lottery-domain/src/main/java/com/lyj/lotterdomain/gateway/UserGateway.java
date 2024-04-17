package com.lyj.lotterdomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.lotterclient.dto.query.UserListByParamQuery;
import com.lyj.lotterdomain.user.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @date 2023-07-02
 **/
public interface UserGateway {

    UserEntity save(UserEntity entity);

    UserEntity findByUserName(Long id, String username);

    IPage<UserEntity> listByParamQuery(@Param("query") UserListByParamQuery query);
}
