package com.wql.project.two.yc;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    //自定义异常
    @ExceptionHandler(CustomException.class)
    public ExceptionResponse handleCustomException(CustomException e) {
        // 可以根据需要定制返回的错误信息或状态码
        return new ExceptionResponse(e.getMessage());
    }

    //token异常处理
    @ExceptionHandler(NotLoginException.class)
    public ExceptionResponse handleNotLogin(NotLoginException e) {
        return new ExceptionResponse(401, e.getMessage());
    }

    //兜底的全局异常
    @ExceptionHandler(Exception.class)
    public ExceptionResponse exception(Exception e) {
        log.error(" handleParameterVerificationException has been invoked", e);
        String msg = null;

        // 注意：这里要判断的是 org.springframework.validation.BindException
        if (e instanceof BindException) {
            FieldError fieldError = ((BindException) e).getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
                log.info(msg);
            }
        } else if (e instanceof RuntimeException) {
            msg = "请求超时，请稍后再试";
        } else {
            msg = "请求超时，请稍后再试";
        }

        return new ExceptionResponse(msg);
    }
}
