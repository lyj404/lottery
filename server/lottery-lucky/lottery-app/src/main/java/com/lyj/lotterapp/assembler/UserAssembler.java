package com.lyj.lotterapp.assembler;

import com.lyj.lotterclient.dto.UserRegisterCmd;
import com.lyj.lotterclient.dto.UserUpdateCmd;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterdomain.user.PassWord;
import com.lyj.lotterdomain.user.UserEntity;
import com.lyj.lotterdomain.user.UserName;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author lyj
 * @date 2023-07-02
 **/
public class UserAssembler {
    private UserAssembler() {
    }

    public static UserEntity toAddEntity(UserRegisterCmd cmd) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(new UserName(cmd.getUsername()));
        userEntity.setPassword(new PassWord(new PassWord.EncryptionPassword(PassWord.getEncryptionPassword(cmd.getPassword()))));
        userEntity.setName(cmd.getName());
        userEntity.setPhone(cmd.getPhone());
        userEntity.setCreateTime(LocalDateTime.now());
        userEntity.setUpdateTime(LocalDateTime.now());
        return userEntity;
    }

    public static UserVO toUserVo(UserEntity entity) {
        UserVO userVO = new UserVO();
        userVO.setId(entity.getId());
        userVO.setUsername(entity.getUsername().getUsername());
        userVO.setName(entity.getName());
        userVO.setPhone(entity.getPhone());
        userVO.setCreateTime(entity.getCreateTime());
        userVO.setUpdateTime(entity.getUpdateTime());
        return userVO;
    }

    public static UserEntity toUpdateEntity(UserUpdateCmd cmd) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(cmd.getId());
        userEntity.setUsername(new UserName(cmd.getUsername()));
        if (Objects.nonNull(cmd.getPassword())) {
            userEntity.setPassword(new PassWord(new PassWord.EncryptionPassword(PassWord.getEncryptionPassword(cmd.getPassword()))));
        }
        userEntity.setName(cmd.getName());
        userEntity.setPhone(cmd.getPhone());
        userEntity.setUpdateTime(LocalDateTime.now());
        return userEntity;
    }
}
