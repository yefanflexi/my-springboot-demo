package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

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
    List<User> findByIdIn(Collection<String> ids);
}
