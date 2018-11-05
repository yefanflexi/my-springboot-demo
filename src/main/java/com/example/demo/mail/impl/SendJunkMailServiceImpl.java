package com.example.demo.mail.impl;

import com.example.demo.mail.SendJunkMailService;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;
@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {

    private static final Logger LOGGER = LogManager.getLogger(SendJunkMailServiceImpl.class);

    @Autowired
    JavaMailSender mailSender;
    @Resource
    private UserService userService;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendJunkMail(List<User> users) {
        try {
            if (users.size()<=0 || users == null) {
                return Boolean.FALSE;
            }
                for(User user:users) {
                    MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                    //邮件发送方
                    mimeMessageHelper.setFrom(from);
                    //邮件主题
                    mimeMessageHelper.setSubject("test mail");
                    //邮件接受方
                    mimeMessageHelper.setTo("yefan_flexi@163.com");
                    //邮件内容
                    mimeMessageHelper.setText("fuck uuuuuuu"+user.getUsername());
                    this.mailSender.send(mimeMessage);
                }

        }catch (Exception e){
            LOGGER.error("sendJunkMail error and users = %s",users,e);
            return Boolean.FALSE;

        }
        return Boolean.TRUE;
    }
}
