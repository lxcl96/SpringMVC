package com.ly.mvc.controller;

import com.ly.mvc.bean.Employee;
import com.ly.mvc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * @FileName:EmployeeController.class
 * @Author:ly
 * @Date:2022/5/30
 * @Description:
 */
@Controller
public class EmployeeController {

    @Autowired //默认byType 不如没有就是ByName
    private EmployeeDao employeeDao;


    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public ModelAndView queryAllEmployee() {
        Collection<Employee> employeeList = employeeDao.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employeeList",employeeList);
        modelAndView.setViewName("employee_list");

        return modelAndView;
    }

    @RequestMapping(value ="/employee/{id}",method = RequestMethod.DELETE)
    public ModelAndView deleteEmployeeById(@PathVariable("id") Integer id) {
        if (id != null) {
            employeeDao.delete(id);
        }
        ModelAndView modelAndView = queryAllEmployee();
        //和直接待用queryAllEmployee 有区别  一个是：请求转发 下面这个是重定向，地址栏会变化
        modelAndView.setViewName("redirect:/employee");//
        return modelAndView;
    }


    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public String addEmployee(String lastName, String email, Integer gender,@RequestBody String requestBody) {
        Employee employee = new Employee(null, lastName, email, gender);
        System.out.println(requestBody);
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee/{id}",method = RequestMethod.PUT)
    public String updateEmployee(@PathVariable("id") Integer id,String lastName, String email, Integer gender) {
        Employee employee = new Employee(id, lastName, email, gender);
        employeeDao.save(employee);
        return "redirect:/employee";
    }
}
