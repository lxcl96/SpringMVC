package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : Ly
 * @Date : 2022/5/29
 * @Description :
 */
@Controller
public class JspController {

    @RequestMapping("/toSuccess")
    public String toSuccess() {
        return "success";
    }
}
