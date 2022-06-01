package com.ly.mvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @FileName:MyInterceptor.class
 * @Author:ly
 * @Date:2022/6/1
 * @Description: 拦截器
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    //控制器方法执行前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1.这里是控制器方法前置拦截器 preHandle！！");
        return true;
    }

    //控制器方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("2.这里是控制器方法后置拦截器 postHandle！！");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //控制器方法后，渲染视图后执行（ModelAndView）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("3.这里是渲染视图后的拦截器 afterCompletion！！");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
