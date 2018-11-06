package com.example.demo.activemq;

import com.example.demo.model.AyMood;
import com.example.demo.service.AyMoodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * activemq消费者
 *
 */
@Component
public class AyMoodConsumer {

    private static final Logger LOGGER = LogManager.getLogger(AyMoodConsumer.class);

    @Resource
    private AyMoodService ayMoodService;
    @JmsListener(destination = "ay.queue")
    public void receiveQueue(String text){
        LOGGER.info("发表"+text+"------------success");
    }

    @JmsListener(destination = "ay.queue.asyn.save")
    public void receiveQueue(AyMood ayMood){
        //ayMoodService.save(ayMood);
        //LOGGER.info("发表ayMood"+ayMood.toString()+"------------success");
    }
}
