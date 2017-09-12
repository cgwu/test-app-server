package com.wyjf.app.web.base;

import com.wyjf.app.api.ApiFactory;
import com.wyjf.app.api.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Created by Administrator on 2017/9/12.
 */
@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult handleValidationException(ConstraintViolationException e) {
        for (ConstraintViolation<?> s : e.getConstraintViolations()) {
            log.info(s.getInvalidValue() + ": " + s.getMessage());
        }
        return ApiFactory.createResult(-1, "请求参数不合法", null);
    }

}
