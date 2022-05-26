package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @FileName:TestController.class
 * @Author:ly
 * @Date:2022/5/25
 * @Description:
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/param")
    public String toParam() {
        return "test_param";
    }
}
