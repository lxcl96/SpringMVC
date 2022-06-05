package com.ly.mvc.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @Author : Ly
 * @Date : 2022/6/5
 * @Description : 我的过滤器
 */

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("我的 ********过滤器");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

}
