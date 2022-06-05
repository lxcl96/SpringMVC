package com.ly.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Properties;

/**
 * @FileName:WebConfig.class
 * @Author:ly
 * @Date:2022/6/2
 * @Description: SpringMVC的配置类 代替springMVC的配置文件
 */
/*
     1.开启注解扫描   2.配置Thymeleaf模板解析   3.开启视图控制器view-controller  4.开启注解驱动器annotation-driver
     5.开启默认servlet处理器 default-servlet-handler   6.开启文件上传解析器   7.开启拦截器    8.异常处理
 */
@Configuration
//1.开启注解扫描
@ComponentScan(basePackages = {"com.ly.mvc"})
//4.开启注解驱动器annotation-driver
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    //代替SpringMVC的配置文件

    //1.开启注解扫描


    //2.配置Thymeleaf模板解析  【***自己写的SpringIOC容器不知道啊，所以不饿能用***】
    /*

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding("utf-8");
        //配置模板解析引擎
        SpringTemplateEngine engine = new SpringTemplateEngine();
        //配置模板引擎解析器
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("utf-8");

        engine.setTemplateResolver(templateResolver);
        viewResolver.setTemplateEngine(engine);

     */


    //3.开启视图控制器view-controller

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toUpload").setViewName("file-upload");
    }


    //4.开启注解驱动器 annotation-driver


    //5.开启默认servlet处理器 default-servlet-handler
    //需要实现一个接口  WebMvcConfigurer
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //表示启动default-servlet  访问静态资源
        configurer.enable();
    }


    //6.开启文件上传解析器  函数名称必须为multipartResolver  就和xml中id必有且为multipartResolver 一样
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    //7.开启拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //通过匿名内部类传入 拦截器 【或者直接写一个拦截器】
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("基于 注解的拦截器！！！");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        }).addPathPatterns("/**"); //拦截所有
    }


    //8.异常处理  既可以用接口的的实现方法configureHandlerExceptionResolvers   也可以直接通过@Bean来创建
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        Properties properties = new Properties();
        properties.setProperty("java.lang.ClassCastException","error");

        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        //exceptionResolver.addStatusCode("error",200);

        //键key 为异常的全类名，值value为出现这个异常要跳转的页面
        exceptionResolver.setExceptionMappings(properties);
        //设置前段获取异常的关键字key  默认为exception
        exceptionResolver.setExceptionAttribute("ex");

        //默认的异常跳转页面 即 发生没有指定的异常时 跳转到error1
        exceptionResolver.setDefaultStatusCode(200);
        exceptionResolver.setDefaultErrorView("error1");
        exceptionResolver.setExcludedExceptions(NullPointerException.class,ArithmeticException.class);

        resolvers.add(exceptionResolver);
    }

    //2.配置Thymeleaf模板解析
    //配置生成模板解析器
    @Bean
    public static ITemplateResolver templateResolver() {
        //对应spring5的ApplicationContext===》针对java工程  ，下面是针对web工程的
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    //生成模板引擎并为模板引擎注入模板解析器  参数根据类型由IOC容器自动装配@Autowire
    @Bean
    public static SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    //生成视图解析器并未解析器注入模板引擎  参数根据类型由IOC容器自动装配@Autowire
    @Bean
    public static ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(1);
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

}
