package com.lyj.config.exception;

/**
 * @author lyj
 * @date 2023-06-29
 **/
public class BaseException extends RuntimeException{
    public BaseException(){
    }

    public BaseException(String message, Object... args){
        super(String.format(message, args));
    }

    public BaseException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
