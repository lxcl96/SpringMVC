package com.ly.mvc.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @FileName:MyFilter.class
 * @Author:ly
 * @Date:2022/6/2
 * @Description:
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("**********======我的！！！过虑器被执行=======*********");
        //过虑器千万别忘记放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
