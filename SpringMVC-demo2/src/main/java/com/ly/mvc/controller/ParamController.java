package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @FileName:ParamController.class
 * @Author:ly
 * @Date:2022/5/26
 * @Description:
 */
@Controller
public class ParamController {
    @RequestMapping("/testServletAPI")
    //前端控制器DispatcherServlet 会根据属性自动输入 HttpServletRequest就在其中
    public String testServletAPI(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ":" + password);
        return "sucess";
    }
}
