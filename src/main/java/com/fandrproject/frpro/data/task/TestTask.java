package com.fandrproject.frpro.data.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by sml
 * 2020/08/08 15:50
 */
@Component
public class TestTask {

    /**
     * 定时任务注解 @Scheduled
     */
 //   @Scheduled(fixedDelay = 300)
    public void printStr() {
        System.out.println("123213212312");
    }
}
