package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolScheduleExample1 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        log.info("begin");

//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.info("shedule");
//            }
//        }, 3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("shedule");
            }
        }, 1, 3, TimeUnit.SECONDS);

//        executorService.shutdown();
    }
}
