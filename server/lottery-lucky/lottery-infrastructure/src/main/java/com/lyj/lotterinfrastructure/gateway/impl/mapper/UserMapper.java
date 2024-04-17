package com.lyj.lotterinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.lotterclient.dto.query.UserListByParamQuery;
import com.lyj.lotterdomain.user.UserEntity;
import com.lyj.lotterinfrastructure.gateway.impl.dataobject.UserDB;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2023-06-30 11:07:44
 * @Entity com.lyj.lotteryuser.po.User
 */
public interface UserMapper extends BaseMapper<UserDB> {

    /**
     * 通过账号查询用户
     *
     * @param id       用户id
     * @param username 用户账号
     * @return 用户信息
     */
    UserDB findByUserName(@Param("id") Long id, @Param("username") String username);

    /**
     * 分页查询用户
     *
     * @param userEntityPage 分页参数
     * @param query          查询参数
     * @return 分页数据
     */
    IPage<UserDB> listByParamQuery(@Param("userEntityPage") Page<UserEntity> userEntityPage, @Param("query") UserListByParamQuery query);
}




