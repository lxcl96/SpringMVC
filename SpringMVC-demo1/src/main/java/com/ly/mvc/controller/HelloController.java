package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @FileName:HelloController.class
 * @Author:ly
 * @Date:2022/5/25
 * @Description: 使用SpringMVC中前端处理器DispatcherServlet 进行请求转发
 * 如下面的：target.html 通过http://localhost:8080/demo1/target 访问到target.html文件
 * 实际此文件是在WEB-INF\templates 目录中，浏览器地址并不是：http://localhost:8080/demo1/WEB-INF/templates/target.html
 * 这个就是请求转发 （而且浏览器根本没办法直接访问WEB-INF目录）
 */

@Controller //一定要加上该注释，否则spring识别不了是控制器，无法通过IOC容器创建
public class HelloController {

    // ip:port ==>显示首页 即 WEB-INF/templates/index.html
    //方法名随意 加上 @RequestMapping才表示是控制器方法
    //请求映射注解，将请求和控制器方法关联 浏览器地址 web工程下 输入/ DispatcherServlet就会解析到这个方法上
    //如果只为value属性赋值，则value可以省略不写
    @RequestMapping(value = "/") //除了请求路径 还可以根据请求方式get/post ，请求参数，请求报文，请求头等进行匹配
    public String index() {

            //返回视图名称  然后就会被thymeleaf解析  即：前缀 + 视图名称 + 后缀  然后跳转到此页面
        return "index";
    }


    //访问 WEB-INF/templates/target.html
    @RequestMapping("/target")  //servlet中 / 表示 ip:port/工程名
    public String target() {
        return "target";
    }
}
