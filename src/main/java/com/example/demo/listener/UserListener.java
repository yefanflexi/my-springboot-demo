package com.example.demo.listener;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * 监听器
 * @author FLEXI
 */
@WebListener
public class UserListener implements ServletContextListener {

    Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;
    private static final String ALL_USER = "ALL_USER_LIST";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
       //查询所有用户
        List<User> userList = userService.findAll();
        //清楚用户缓存
        redisTemplate.delete(ALL_USER);
        //将数据存放到redis
        redisTemplate.opsForList().leftPushAll(ALL_USER,userList);
        /*
            在实际项目中需注销
         */
        List<User> queryUserList = redisTemplate.opsForList().range(ALL_USER,0,-1);
        logger.info(queryUserList.size()+"renshu");
        logger.info("ServletContext init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("ServletContext destory");

    }
}
