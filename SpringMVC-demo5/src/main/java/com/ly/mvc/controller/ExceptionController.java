package com.ly.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.rmi.AccessException;

/**
 * @FileName:ExceptionController.class
 * @Author:ly
 * @Date:2022/6/2
 * @Description: 基于注解的异常处理
 */

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ClassCastException.class,ClassNotFoundException.class})
    //@RequestMapping("/testAnnotationException")  不需要mapping了，因为这个专门处理异常的
    //Exception 就是捕获的异常   model代表传递回前端数据
    public String testAnnotationException(Exception exception, Model model) {
        //exception就是传回的 键key
        model.addAttribute("exception",exception);
        return "error";
    }

    @ExceptionHandler({AccessException.class})
    //@RequestMapping("/test")  仅处理异常映射，不处理前端请求
    public String test() {
        return "error";
    }
}
