package com.gxj.graduation.controller;

import com.gxj.graduation.entity.User;
import com.gxj.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(Model model, User user){
        if(userService.queryById(user.getId())==null){
            user.setPriority("guest");
            userService.addUser(user);
            return "success";
        }
        else{
            return "failure";
        }
    }

    @RequestMapping("/login")
    public String login(Model model,User user, HttpServletResponse response) throws UnsupportedEncodingException {
        User userById = userService.queryById(user.getId());
        if(userById != null){
            System.out.println("查询："+userById.getId());
            System.out.println("查询："+userById.getPassword());
            System.out.println("输入："+user.getId());
            System.out.println("输入："+user.getPassword());
            if(userById.getPassword().equals(user.getPassword())){
                //将用户名存入cookie 并且设置cookie存在时长
                Cookie cookie_userId = new Cookie("userId", URLEncoder.encode(user.getId(),"utf-8"));
                cookie_userId.setMaxAge(60*60*60);
                response.addCookie(cookie_userId);

                System.out.println("success");
                return "businessFlowChart";
            }
            System.out.println("failure");
        }
        return "failure";
    }
}
