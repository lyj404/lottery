package com.lyj.config.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author lyj
 * @date 2023-06-29
 **/
@Builder
@ToString
@Getter
public class FailResult extends CommonResult{
    public static final Integer DEFAULT_CODE = 50000;
    protected static final String DEFAULT_MESSAGE = "操作失败";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String exception;

    protected FailResult(String exception) {
        super(false, DEFAULT_CODE, DEFAULT_MESSAGE);
        this.exception = exception;
    }
    public FailResult(Integer code, String exception) {
        super(false, code, DEFAULT_MESSAGE);
        this.exception = exception;
    }
}
