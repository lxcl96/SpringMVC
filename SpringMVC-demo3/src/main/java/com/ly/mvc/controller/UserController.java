package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author : Ly
 * @Date : 2022/5/29
 * @Description : 使用RESTFUL模拟用户资源的增删改查
 */
@Controller
public class UserController {

    /**
     * 查询所有用户信息
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ModelAndView queryAllUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("key","查询所有用户信息!");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public ModelAndView queryUserById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("key","查询id为" + id + "的用户信息!");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 新增用户
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ModelAndView addUser(String username,String password) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("key","新增用户信息：\n" + username + ":" + password);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 根据用户id删除用户信息
     * @param id 用户id
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("key","删除id为"+ id + " 的用户信息!");
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /**
     * 根据用户id更新用户信息
     * @param id 用户id
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    public ModelAndView updateUser(String username,String password, @PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("key","修改id为"+ id + " 的用户信息:\n" + username + ":" + password);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
