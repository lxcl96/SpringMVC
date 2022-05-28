package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : Ly
 * @Date : 2022/5/28
 * @Description : 视图解析
 */
@Controller
public class ViewController {
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf() {
        return "success";
    }


    @RequestMapping("/testForward")
    public String testForward() {

        // 服务器端其中 / 表示ip:port/工程路径
        //request.getRequestDispatcher("路径").forward(request,response)
        // return "forward:/success";
        return "forward:success"; // 加没加斜杠 / 都一样，都表示ip:port/demo3(工程路径)/success
    }

    @RequestMapping("/testForward1")
    public String testForward1() {
        //request.getRequestDispatcher("路径").forward(request,response)
        return "forward:/success";// 服务器端其中 / 表示ip:port/工程路径
    }

    @RequestMapping("/testForward2")
    public String testForward2() {
        return "forward:testRequestByServletAPI";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect() {
        return "redirect:success";
    }

    @RequestMapping("/testRedirect2")
    public String testRedirect2() {
        return "redirect:testRequestByServletAPI";
    }
}
