package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "success";
    }
}
