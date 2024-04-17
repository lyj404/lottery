package com.lyj.lotterinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.config.enums.ExceptionEnum;
import com.lyj.config.utils.AssertUtil;
import com.lyj.lotterclient.dto.query.UserListByParamQuery;
import com.lyj.lotterdomain.gateway.UserGateway;
import com.lyj.lotterdomain.user.UserEntity;
import com.lyj.lotterinfrastructure.convertor.UserConverter;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.UserDB;
import com.lyj.lotterinfrastructure.gateway.impl.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-02
 **/
@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {
    private final UserMapper userMapper;

    @Override
    public UserEntity save(UserEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addUser(entity);
        }
        return updateUser(entity);
    }

    @Override
    public IPage<UserEntity> listByParamQuery(UserListByParamQuery query) {
        IPage<UserDB> iPage = userMapper.listByParamQuery(new Page<>(query.getPageIndex(), query.getPageSize()), query);
        return iPage.convert(UserConverter::toEntity);
    }

    private UserEntity updateUser(UserEntity entity) {
        UserDB userDB = UserConverter.toUserDB(entity);
        AssertUtil.isTrue(userMapper.updateById(userDB) <= 0, ExceptionEnum.UPDATE_ERROR.getDescription());
        return UserConverter.toEntity(userDB);
    }

    private UserEntity addUser(UserEntity entity) {
        UserDB userDB = UserConverter.toUserDB(entity);
        int i = userMapper.insert(userDB);
        AssertUtil.isTrue(i <= 0, ExceptionEnum.ADD_ERROR.getDescription());
        return UserConverter.toEntity(userDB);
    }

    @Override
    public UserEntity findByUserName(Long id, String username) {
        UserDB userDB = userMapper.findByUserName(id, username);
        if (Objects.isNull(userDB)) {
            return null;
        }
        return UserConverter.toEntity(userDB);
    }
}
