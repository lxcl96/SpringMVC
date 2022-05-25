package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @FileName:RequestMappingController.class
 * @Author:ly
 * @Date:2022/5/25
 * @Description:
 */
@Controller
//先匹配类的路径 才会去匹配类的方法
//总结：类下的所有方法 的请求地址都会加上  hello 如/testRequestMapping 就变成 /hello/testRequestMapping
@RequestMapping("/hello") //放在类上或接口上 表示请求必须符合类的路径，才能访问下面的方法 否则就算是路径相同也无法访问到方法
public class RequestMappingController {
    //@target = ElementType.TYPE   可放在接口、类、枚举

    //@target = ElementType.METHOD 放在方法上直接和请求建立连接
    @RequestMapping("/testRequestMapping")
    public String success() {
        return "success";
    }
}
