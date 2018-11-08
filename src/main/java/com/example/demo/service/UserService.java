package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * user 服务层接口
 */
public interface UserService {
    User findById(String id);
    List<User> findAll();
    User save (User user);
    void delete(String id);
    //分页
    Page<User> findAll(Pageable pageable);

    List<User> findByName(String name);
    User findByUserName(String name);
    List<User> findByIdIn(Collection<String> ids);
    //mybatis
    User findByUsernameAndPassword(String username,String password);
    User findByUsernameAndPasswordRetry(String username,String password);
    Future<List<User>> findAsynAll();
}
