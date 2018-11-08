package com.example.demo.api.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.demo.api.UserService;
import com.example.demo.domain.User;

@Service(version = "1.0")
public class UserServiceImpl implements UserService {


    @Override
    public User findByUsernameAndPassword(String username, String password) {
        //连接数据库 此处省略
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        return user;
    }
}
