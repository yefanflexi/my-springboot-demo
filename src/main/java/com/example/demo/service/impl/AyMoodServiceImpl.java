package com.example.demo.service.impl;

import com.example.demo.activemq.AyMoodProducer;
import com.example.demo.model.AyMood;
import com.example.demo.repository.AyMoodRepository;
import com.example.demo.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class AyMoodServiceImpl implements AyMoodService {

    @Resource
    private AyMoodRepository ayMoodRepository;

    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }

    //队列
    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");

    @Resource
    private AyMoodProducer ayMoodProducer;
    @Override
    public String asynSave(AyMood ayMood) {

        //往队列ay.queue.asyn.save推送消息，内容为说说主体
        ayMoodProducer.sendMessage(destination,ayMood);
        return "success";
    }

}
