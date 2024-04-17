package com.lyj.config.enums;

import com.lyj.config.vo.FailResult;
import lombok.Getter;

/**
 * @author lyj
 * @date 2023-07-07
 **/
@Getter
public enum ExceptionEnum {
    /**
     * 添加和修改错误
     */
    ADD_ERROR(FailResult.DEFAULT_CODE, "修改数据失败"),
    UPDATE_ERROR(FailResult.DEFAULT_CODE, "插入数据失败");
    private Integer code;

    private String description;

    ExceptionEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}