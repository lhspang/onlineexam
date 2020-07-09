package com.sen.onlineexam.exception;

import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@ResponseBody
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseResult error(HttpServletRequest request, HttpServletResponse response,Exception e){
        e.printStackTrace();
        return ResponseResult.failure(ResultCode.SERVER_ERROR);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseResult error(){
        return ResponseResult.failure(ResultCode.NOT_AUTHORIZED);
    }
}
