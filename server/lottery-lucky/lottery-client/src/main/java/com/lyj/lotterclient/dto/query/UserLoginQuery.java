package com.lyj.lotterclient.dto.query;

import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @author lyj
 * @date 2023-06-30
 **/
@Data
public class UserLoginQuery extends Query {
    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
