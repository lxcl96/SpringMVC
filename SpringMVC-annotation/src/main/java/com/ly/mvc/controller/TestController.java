package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @FileName:TestController.class
 * @Author:ly
 * @Date:2022/6/2
 * @Description:
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "index";
    }
}
