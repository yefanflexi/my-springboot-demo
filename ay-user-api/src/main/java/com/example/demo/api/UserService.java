package com.example.demo.api;

import com.example.demo.domain.User;


/**
 * user 服务层接口
 */
public interface UserService {

    User findByUsernameAndPassword(String username, String password);

}
