package com.lyj.common.handler;

import com.lyj.common.enums.ExceptionEnum;
import com.lyj.config.exception.BaseException;
import com.lyj.config.exception.CodeException;
import com.lyj.config.exception.TokenAuthException;
import com.lyj.config.vo.FailResult;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author lyj
 * @date 2023-06-29
 **/
@Slf4j
@RestControllerAdvice
public class SysExceptionHandler {

    /**
     * 最大的兜底错误处理
     *
     * @param ex Exception
     * @return FailResult
     */
    @ExceptionHandler(value = Exception.class)
    public FailResult systemException(Exception ex) {
        log.error(ExceptionEnum.EXCEPTION_INFO_DESC.getDescription(), ex.getMessage());
        log.error(ExceptionEnum.EXCEPTION_INFO.getDescription(), ex);
        return FailResult.builder().exception(ex.getMessage()).build();
    }

    /**
     * 参数绑定错误
     *
     * @param ex BindException
     * @return FailResult
     */
    @ExceptionHandler(value = BindException.class)
    public FailResult bindException(BindException ex) {
        String defaultMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        log.error(ExceptionEnum.EXCEPTION_INFO_DESC.getDescription(), defaultMessage);
        log.error(ExceptionEnum.EXCEPTION_INFO.getDescription(), ex);
        return FailResult.builder().exception(defaultMessage).build();
    }

    @ExceptionHandler(value = CodeException.class)
    public FailResult codeException(CodeException ex) {
        log.error(ExceptionEnum.EXCEPTION_INFO_DESC.getDescription(), ex.getMessage());
        log.error(ExceptionEnum.EXCEPTION_INFO.getDescription(), ex);
        return new FailResult(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = BaseException.class)
    public FailResult baseException(BaseException ex) {
        log.error(ExceptionEnum.EXCEPTION_INFO_DESC.getDescription(), ex.getMessage());
        log.error(ExceptionEnum.EXCEPTION_INFO.getDescription(), ex);
        return FailResult.builder().exception(ex.getMessage()).build();
    }

    @ExceptionHandler(value = TokenAuthException.class)
    public FailResult tokenAuthException(Exception ex) {
        log.error(ExceptionEnum.EXCEPTION_INFO_DESC.getDescription(), ex.getMessage());
        log.error(ExceptionEnum.EXCEPTION_INFO.getDescription(), ex);
        return FailResult.builder().exception(ex.getMessage()).build();
    }

    @ExceptionHandler(value = MysqlDataTruncation.class)
    public FailResult mysqlDataTruncation(Exception ex) {
        log.error(ExceptionEnum.EXCEPTION_INFO_DESC.getDescription(), ex.getMessage());
        log.error(ExceptionEnum.EXCEPTION_INFO.getDescription(), ex);
        return new FailResult(500, ex.getMessage());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public FailResult dataIntegrityViolationException(Exception ex) {
        log.error(ExceptionEnum.EXCEPTION_INFO_DESC.getDescription(), ex.getMessage());
        log.error(ExceptionEnum.EXCEPTION_INFO.getDescription(), ex);
        String message = ex.getMessage();
        String[] split = message.split("\r\n###");
        for (String str : split) {
            if (str.trim().isBlank() || str.trim().contains("Error")) {
                continue;
            }
            String[] split1 = str.split(":");
            if (split1.length > 0) {
                message = split1[split1.length - 1].trim();
            }
        }
        return new FailResult(500, message);
    }
}