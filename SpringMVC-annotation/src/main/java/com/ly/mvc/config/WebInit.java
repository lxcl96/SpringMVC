package com.ly.mvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;

/**
 * @FileName:WebInit.class
 * @Author:ly
 * @Date:2022/6/2
 * @Description: 配置类 代替web.xml
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     *指定Spring配置类
     * @return 返回spring配置类的class
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {

        //返回spring配置类的class  没有配置类返回Class[0] 长度为0的数组
        return new Class[]{SpringConfig.class};
    }

    /**
     * 指定SpringMVC的配置类   代替SpringMVC的配置文件
     * @return 返回SpringMVC配置类的class
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //返回springMVC配置类的class  没有配置类返回Class[0] 长度为0的数组
        return new Class[]{WebConfig.class};
    }

    /**
     * 指定DispatcherServlet的映射路径 (和web.xml一样处理所有请求)
     * @return 返回映射路径
     */
    @Override
    protected String[] getServletMappings() {
        //return new String[0] 一个servlet可以有多个url-pattern 所以是数组
        return new String[]{"/"}; //接收所有请求 除jsp外
    }

    /**
     * 设置servlet的过虑器
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        //两个过虑器 1.HiddenHttpMethodFilter  2.CharacterEncodingFilter
        //设置请求和响应编码
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("utf-8",true);
        System.out.println("*************接收到servlet两个过虑器************");
        //接收put ，delete，patch请求
        HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{encodingFilter,httpMethodFilter};

        //???疑问为什么没配置过虑器的路径url-pattern

    }
}
