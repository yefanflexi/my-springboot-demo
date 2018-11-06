package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private UserDao userDao;
    @Resource
    private UserRepository userRepository;
    @Resource
    private RedisTemplate redisTemplate;
    private static final String ALL_USER = "ALL_USER_LIST";
    @Override
    public User findById(String id) {
        List<User> userList = redisTemplate.opsForList().range(ALL_USER,0,-1);
        if (userList != null && userList.size() >0){
            for (User user:userList){
                if (user.getId().equals(id)){
                    return user;
                }
            }
        }
        //查询数据库中数据
        User user = userRepository.findOne(id);
        if (user != null){
            //查询到新的数据 重新插入redis缓存中
            redisTemplate.opsForList().leftPush(ALL_USER,user);
        }
        return user;
    }

    @Override
    @Async
    public Future<List<User>> findAsynAll() {
        try {
            logger.info("开始异步findAll任务");
            Long start = System.currentTimeMillis();
            List<User> userList = userRepository.findAll();
            Long end = System.currentTimeMillis();
            logger.info("完成耗时："+(end-start)+"毫秒");
            return new AsyncResult<List<User>>(userList);

        }catch (Exception e){
            logger.error("method [findAll] error",e);
            return new AsyncResult<List<User>>(null);

        }
    }

    @Override
    public List<User> findAll() {
        try {
            logger.info("开始findAll任务");
            Long start = System.currentTimeMillis();
            List<User> userList = userRepository.findAll();
            Long end = System.currentTimeMillis();
            logger.info("完成耗时："+(end-start)+"毫秒");
            return userList;

        }catch (Exception e){
            logger.error("method [findAll] error",e);
            return Collections.EMPTY_LIST;

        }
       // return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        userRepository.delete(id);
        logger.info("userId:"+id+"用户被删除");
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findByIdIn(Collection<String> ids) {
        return userRepository.findByIdIn(ids);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
}
