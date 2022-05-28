package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareConcurrentModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author : Ly
 * @Date : 2022/5/28
 * @Description :
 */
@Controller
public class ScopeController {

    /**
     * 通过原生的servlet，来设置request域对象属性值
     * @param request 请求
     * @return 成功页面
     */
    @RequestMapping("/testRequestByServletAPI")
    public String testRequestByServletAPI(HttpServletRequest  request) {
        request.setAttribute("testRequestByScope","hello,servletAPI");
        //request.getRequestDispatcher().forward(request,response);
        HttpSession session = request.getSession();
        session.setAttribute("sessionKey","sessionScope");

        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("servletContextKey","servletContextScope");
        //这个是请求转发：1、web-info下重定向访问不了 2、地址栏地址没变化
        return "success";
    }

    /**
     *两个功能：1、向request域共享数据；2、设置视图名称
     * @return 返回值必须是ModelAndView类型的给前端控制器DispatcherServlet使用（才能解析跳转到指定页面），因为其有两个功能：模型和视图，因此必须返回这个
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView view = new ModelAndView();
        //处理模型数据，即向请求域request域共享数据
        view.addObject("testRequestByScope","hello,ModelAndView");

        //设置视图名称，返回给前端处理器解析
        view.setViewName("success");

        System.out.println(view);
        return view;
    }

    /**
     * 使用Model对象向request域共享数据
     * @param model Model类型的形参，就是ModelAndView中的Model，会被SpringMVC自动注入
     * @return 成功页面
     */
    @RequestMapping("/testModel")
    public String testModel(Model model) { //肯定是传参拿，否则自己创建Model，SpringMVC怎么知道呢？
        model.addAttribute("testRequestByScope","hello,Model");
        System.out.println(model);
        return "success";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map) {
        map.put("testRequestByScope","hello,Map");
        System.out.println(map);
        return "success";
    }



    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap map) {
       map.addAttribute("testRequestByScope","hello,ModelMap");
       map.addAttribute("qqqqq","hello,qqqq");
       map.addAttribute("qqwwwwwqqq","hello,qqqwwwwq");
        System.out.println(map);
       return "success";
    }

    @RequestMapping("/testSession")
    public String testSession(HttpSession session) {
        session.setAttribute("sessionKey","testSessionScope");
        return "success";
    }

    @RequestMapping("testServletContext")
    public String testServletContext(HttpSession httpSession) {
        ServletContext servletContext = httpSession.getServletContext();
        servletContext.setAttribute("servletContextKey","hello,application");
        return "success";
    }
}
