package com.gxj.graduation.controller;

import com.gxj.graduation.entity.User;
import com.gxj.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(Model model,User user){
        User userById = userService.queryById(user.getId());
        if(userById != null){
            System.out.println("查询："+userById.getId());
            System.out.println("查询："+userById.getPassword());
            System.out.println("输入："+user.getId());
            System.out.println("输入："+user.getPassword());
            if(userById.getPassword().equals(user.getPassword())){
                System.out.println("success");
                return "success";
            }
            System.out.println("failure");
        }
        return "failure";
    }
}
