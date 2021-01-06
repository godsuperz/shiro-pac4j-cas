package com.superz.shirospringboot.common.exception;

import com.superz.shirospringboot.common.entity.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhang.chao
 * @date 2021/1/4
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseResult<?> handleException(AuthorizationException e) {

        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        log.debug("AuthorizationException was thrown", e);

        ResponseResult<Object> result = new ResponseResult<>();
        result.setCode(HttpStatus.FORBIDDEN.value());
        result.setMessage("No message available");
        return result;
    }
}
