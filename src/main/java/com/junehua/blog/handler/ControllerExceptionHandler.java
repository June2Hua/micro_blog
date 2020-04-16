package com.junehua.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一的异常处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    public ModelAndView exception(Exception exception, HttpServletRequest request) {
        //日志记录
        logger.error("请求地址为：{}---错误为：{}",request.getRequestURL(),exception.getMessage());
        ModelAndView modelAndView = new ModelAndView("error/error");// error页面
        modelAndView.addObject("errorMessage", exception.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        return modelAndView;
    }

}
