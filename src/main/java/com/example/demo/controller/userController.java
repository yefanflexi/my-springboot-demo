package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class userController {
    @Resource
    private UserService userService;

    @RequestMapping("/test")
    public String test(Model model){
        //查询所有用户
        List<User> userList = userService.findAll();
        model.addAttribute("users",userList);
        return "userList";
    }
}
