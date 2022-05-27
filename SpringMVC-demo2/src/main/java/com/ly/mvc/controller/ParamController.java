package com.ly.mvc.controller;

import com.ly.mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        //session为服务器创建，依赖于cookie
        // 服务器第一次获取session如果请求中没有，就默认创建一个session，并在响应中返回
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ":" + password);
        return "success";
    }

    @RequestMapping("/testControlMethod")
    public String testControlMethod(@RequestParam("user_name") String username,String password,
                                    @RequestParam("hobby") String[] hobby,@RequestBody() String reqBody) {
        System.out.println(username + ":" + password);
        for (String s : hobby) {
            System.out.print("爱好：" + s);
        }

        System.out.println("***************");
        System.out.println(reqBody);
        System.out.println("***************");
        return "success";
    }
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader("host") String host) {
        System.out.println("host:"+ host);
        return "success";
    }

    @RequestMapping("/testCookie")
    //value参数为cookie的id，即每次只能查找指定的cookie,得到的是指定cookie的value值
    public String testCookie(@CookieValue("JSESSIONID") String cookie) {
        System.out.println("cookie = " + cookie);
        return "success";
    }


    @RequestMapping("/testPojo")
    //测试通过POJO传递参数
    public String testPojo(User user) {
        System.out.println(user);
        return "success";
    }
}
