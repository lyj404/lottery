package com.lyj.config.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author lyj
 * @date 2023-06-29
 **/
@Getter
public abstract class CommonResult implements Serializable {
    protected final Boolean result;
    protected final Integer code;
    /**
     * message为null,则不序列化
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected final String message;

    protected CommonResult(Boolean result, Integer code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }
}
