package com.plm.handler;

import com.plm.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : cwh
 * 2019/2/22 0022
 * description ：
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handler(UserException exception) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", exception.getCode());
        result.put("message", exception.getMessage());
        return result;
    }
}
