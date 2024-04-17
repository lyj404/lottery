package com.lyj.config.exception;

import com.lyj.config.vo.FailResult;

/**
 * @author lyj
 * @date 2023-06-29
 **/
public class CodeException extends RuntimeException {
    private final Integer code;

    public Integer getCode() {
        return code;
    }

    public CodeException(Integer code) {
        this.code = code;
    }

    public CodeException(Integer code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public CodeException(String message, Object... args) {
        super(String.format(message, args));
        this.code = FailResult.DEFAULT_CODE;
    }

    public CodeException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
        this.code = FailResult.DEFAULT_CODE;
    }

    public CodeException(Throwable cause) {
        super(cause);
        this.code = FailResult.DEFAULT_CODE;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
