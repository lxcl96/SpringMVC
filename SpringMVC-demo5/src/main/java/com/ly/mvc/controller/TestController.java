package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.rmi.AccessException;

/**
 * @FileName:TestController.class
 * @Author:ly
 * @Date:2022/6/1
 * @Description:
 */
@Controller
public class TestController {
    @RequestMapping("/testInterceptor")
    public String testInterceptor() {
        int i = 1 / 0;
        return "success";
    }

    @RequestMapping("/testException")
    public String testException() {
        throw new NullPointerException("哎呀，出错了！");
    }

    @RequestMapping("/testException1")
    public String testException1() {
        throw new ClassCastException("哎呀，出错了！");
    }

    @RequestMapping("/testException2")
    public String testException2() throws AccessException {
        throw new AccessException("哎呀，出错了！");
    }


}
