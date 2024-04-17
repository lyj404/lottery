package com.lyj.lotterinfrastructure.convertor;

import com.lyj.lotterdomain.user.PassWord;
import com.lyj.lotterdomain.user.UserEntity;
import com.lyj.lotterdomain.user.UserName;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.UserDB;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-02
 **/
public class UserConverter {
    private UserConverter() {
    }

    public static UserDB toUserDB(UserEntity entity) {
        UserDB userDB = new UserDB();
        userDB.setId(entity.getId());
        userDB.setUsername(entity.getUsername().getUsername());
        if (Objects.nonNull(entity.getPassword())) {
            userDB.setPassword(entity.getPassword().getEncryptionPassword().getPassword());
        }
        userDB.setPhone(entity.getPhone());
        userDB.setName(entity.getName());
        userDB.setCreateTime(entity.getCreateTime());
        userDB.setUpdateTime(entity.getUpdateTime());
        return userDB;
    }

    public static UserEntity toEntity(UserDB userDB) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDB.getId());
        userEntity.setUsername(new UserName(userDB.getUsername()));
        userEntity.setPassword(new PassWord(new PassWord.EncryptionPassword(userDB.getPassword())));
        userEntity.setName(userDB.getName());
        userEntity.setPhone(userDB.getPhone());
        userEntity.setCreateTime(userDB.getCreateTime());
        userEntity.setUpdateTime(userDB.getUpdateTime());
        return userEntity;
    }
}
