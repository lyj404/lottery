package com.lyj.lotterclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @date 2023-06-30
 **/
@Data
public class UserVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

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
