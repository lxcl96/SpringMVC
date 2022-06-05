package com.ly.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @FileName:TestController.class
 * @Author:ly
 * @Date:2022/6/2
 * @Description:
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "index";
    }

    @RequestMapping("/upload") //文件必须要同名photo 不然传递不过来
    public String upload(HttpSession session, MultipartFile myPhoto,String username) throws IOException {

        //获取web工程的绝对路径
        String realPath = session.getServletContext().getRealPath("/upload/photo");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取文件大小，设置缓冲区  下载时才用到 上传用不到
        //byte[] bytes = new byte[(int) (photo.getSize()) + 1];

        //获取文件名 设置为UUID解决重名问题
        String originalFilename = myPhoto.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String fileName = realPath + File.separator + uuid.toString() + suffix;
        session.setAttribute("uuid",uuid.toString() + suffix);
        System.out.println(username);
        System.out.println(uuid);
        //获取文件 将数据读到缓冲区内
        myPhoto.transferTo(new File(fileName));
        return "hello";
    }

    @RequestMapping("/toException")
    public void toException() {
        throw new ClassCastException();
    }

    @RequestMapping("/toException1")
    public void toException1() throws ClassNotFoundException {
        throw new ClassNotFoundException();
    }
}
