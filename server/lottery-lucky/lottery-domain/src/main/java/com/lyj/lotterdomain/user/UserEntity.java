package com.lyj.lotterdomain.user;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-06-30
 **/
@Entity
@Data
public class UserEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private UserName username;

    /**
     * 密码
     */
    private PassWord password;

    /**
     * 名字
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
