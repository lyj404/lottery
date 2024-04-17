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
@Getter
@ToString
public class SuccessResult extends CommonResult{
    protected static final Integer DEFAULT_CODE = 20000;
    protected static final String DEFAULT_MESSAGE = "操作成功";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Object data;


    protected SuccessResult(Object data) {
        super(true, DEFAULT_CODE, DEFAULT_MESSAGE);
        this.data = data;
    }
}
