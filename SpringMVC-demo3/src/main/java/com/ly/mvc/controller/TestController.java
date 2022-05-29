package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author : Ly
 * @Date : 2022/5/28
 * @Description :
 */
@Controller
public class TestController {

//    @RequestMapping("/")
//    public String toIndex() {
//        return "index";
//    }


    @RequestMapping("/test_view")
    public String test_view() {
        return "test_view";
    }

    @RequestMapping("/test_rest")
    public String test_rest() {
        return "test_rest";
    }
}
