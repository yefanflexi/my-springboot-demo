package com.example.demo.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @deprecated 定时器
 * @author FLEXI
 */
public class TestTask {
    //日志对象
    private static final Logger LOGGER = LogManager.getLogger(TestTask.class);

    public void run(){
        LOGGER.info("定时器 runing");
    }

}
