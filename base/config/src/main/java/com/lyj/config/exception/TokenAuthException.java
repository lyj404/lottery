package com.lyj.config.exception;

/**
 * @author lyj
 * @date 2023-07-03
 **/
public class TokenAuthException extends RuntimeException {
    public TokenAuthException() {
    }

    public TokenAuthException(String message, Object... args) {
        super(String.format(message, args));
    }

    public TokenAuthException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

    public TokenAuthException(Throwable cause) {
        super(cause);
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
