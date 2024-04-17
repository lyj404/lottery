package com.lyj.lotterclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author lyj
 * @date 2023-06-30
 **/
@Data
public class UserListByParamQuery extends PageQuery {
    private Long id;
    private String name;
    private String phone;
}
