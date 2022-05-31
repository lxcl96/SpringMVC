package com.ly.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @FileName:FileUpAndDownController.class
 * @Author:ly
 * @Date:2022/5/31
 * @Description: 实现基于SpringMVC的文件上传和下载
 */
@Controller
public class FileUpAndDownController {

    @RequestMapping("/testFileUp")
    //photot 对应前端照片的name  即获取参数 ,session是为了确定服务器真实存放路径
    public String testFileUp(MultipartFile photo,HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("photo");

        File directory = new File(realPath + File.separator);
        if (!directory.exists()) {
            boolean mkdirs = directory.mkdirs();
            System.out.println(mkdirs?directory+ "创建成功":directory + "创建失败");
        }
        photo.transferTo(new File(realPath + File.separator + photo.getOriginalFilename()));
        //表单元素的name属性值 即参数key
        System.out.println(photo.getName());
        //完整的文件名
        System.out.println(photo.getOriginalFilename());
        return "success";
    }




    @RequestMapping("/testFileDown")
    public ResponseEntity<byte[]> testFileDown(HttpSession session) throws IOException {
        //获取servletContext对象
        ServletContext servletContext = session.getServletContext();
        //要下载的获取文件在服务器的真实路径
        String realPath = servletContext.getRealPath("/static/img/summer.jpg");
        System.out.println(realPath);
        //创建输入流
        FileInputStream is = new FileInputStream(realPath);
        byte[] bytes = new byte[is.available()];
        is.read(bytes);

        //创建HttpHeader对象，并设置响应头信息
        MultiValueMap<String,String> httpHeaders = new HttpHeaders();
        //设计下载标记
        httpHeaders.add("Content-Disposition","attachment;filename=summer.jpg");

        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建报文对象 ResponseEntity
        is.close();
        return new ResponseEntity<byte[]>(bytes,httpHeaders,statusCode);
    }
}
