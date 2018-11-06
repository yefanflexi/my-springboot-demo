package com.example.demo.quartz;

import com.example.demo.mail.SendJunkMailService;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时器类
 *
 */
@Component
@Configurable
@EnableScheduling
public class SendMailQuartz {
    //日志对象
    private static final Logger LOGGER = LogManager.getLogger(SendMailQuartz.class);

    @Resource
    private SendJunkMailService sendJunkMailService;
    @Resource
    private UserService userService;

    //每5秒执行一次
    @Scheduled(cron="0/5 * *  * * * ")
    public void reportCurrentByCron(){
        List<User> userList = userService.findAll();
        if (userList.size()<=0 || userList == null) {
            return ;
        }
//        sendJunkMailService.sendJunkMail(userList);
        LOGGER.info("定时器 runing ! ! !");
    }
}
