package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
//    @RequestMapping("/testRequestMapping")
//    @GetMapping("/testRequestMapping")
//    @RequestMapping(value = {"/testRequestMapping",
//            "/test"},
//            method = {
//                RequestMethod.POST
//            }
//    ) //如果value是数组，则表示可以匹配其中任意一个路径
//    public String success() {
//        return "success";
//    }
//
//
//    @RequestMapping(value = "/testPut",method = RequestMethod.PUT)
//    public String testPut() {
//        return "success";
//    }
//
//    /*
//        params = {"username"} 表示请求中必须含有关建字（key）username，对属性值（value）不做要求
//        params = {"!username"} 表示请求中不能含有关建字（key）username
//        params = {"username=admin"} 表示请求中必须含有关建字（key）username，且属性值（value）必须为admin
//        params = {"username!=admin"} 表示请求中必须含有关建字（key）username，且属性值（value）不能为为admin
//
//     */
//    @RequestMapping(value = "/testParams1",params = {"username"},headers = {"host"})
//    public String testParameters1() {
//        return "success";
//    }
//
//    @RequestMapping(value = "/testParams2",params = {"!username"},headers = {"!host"})
//    public String testParameters2() {
//        return "success";
//    }
//
//    @RequestMapping(value = "/testParams3",params = {"username=admin"},headers = {"host=localhost:8080"})
//    public String testParameters3() {
//        return "success";
//    }
//    @RequestMapping(value = "/testParams4",params = {"username!=admin"},headers = {"host!=localhost:8080"})
//    public String testParameters4() {
//        return "success";
//    }
//
//    @RequestMapping("/?")
//    public String testAnt1() {
//        return "success";
//    }
//
//    @RequestMapping("/*")
//    public String testAnt2() {
//        return "success1";
//    }
//
//    @RequestMapping("/**/aaa")
//    public String testAnt3() {
//        return "success1";
//    }


    //测试springMVC 路径占位符
    @RequestMapping("/testPath/{id}/{username}") //{}就表示为占位符 表示这是前端传来的值而不是地址,随便起个名字叫id
    public String testPath(@PathVariable("id") Integer id,@PathVariable("username") String username) {
        System.out.println("id:" + id);
        System.out.println("username:" + username);
        return "success";
    }
}
