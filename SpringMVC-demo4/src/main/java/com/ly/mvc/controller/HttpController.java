package com.ly.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mvc.bean.User;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @FileName:HttpController.class
 * @Author:ly
 * @Date:2022/5/30
 * @Description:
 */
@Controller
public class HttpController {

//    @RequestMapping(value = "/testRequestBody",method = RequestMethod.POST)
//    public String testRequestBody(@RequestBody String requestBody) {
//        System.out.println(requestBody);
//        return "success";
//    }


    @RequestMapping(value = "/testRequestBody",method = RequestMethod.POST)
    public String testRequestBody(RequestEntity<String> requestEntity) {
        System.out.println(requestEntity);
        System.out.println(requestEntity.getMethod());
        System.out.println(requestEntity.getType());
        System.out.println(requestEntity.getUrl());
        System.out.println(requestEntity.getBody());
        System.out.println(requestEntity.getHeaders());
        HttpHeaders headers = requestEntity.getHeaders();
        return "success";
    }


    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody() {
        return "<h1> testResponseBody 成功！</h1>";
    }

    @RequestMapping("/testResponseUser")
    @ResponseBody
    public User testResponseUser() {
        return new User(1,"张三",22,"男");
    }

    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username,String password) {
        System.out.println(username + ":" + password);
        return "hello,axios!";
    }
}
