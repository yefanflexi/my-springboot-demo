package com.example.demo.mail;

import com.example.demo.model.User;

import java.util.List;

/**
 * send user mail service
 */
public interface SendJunkMailService {
    boolean sendJunkMail(List<User> users);
}
