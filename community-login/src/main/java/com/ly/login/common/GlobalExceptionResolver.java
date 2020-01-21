package com.ly.login.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理全局异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(Exception.class)
    public ServerResponse<String> resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.error("{}Exception",request.getRequestURI(),ex);

        return ServerResponse.createByErrorCodeMsg(ResponseCode.ERROR.getCode(),"接口异常");
    }
}
