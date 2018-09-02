package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NewScheduledThreadPoolExample1 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++) {
            final int index = i;
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    log.info("task: {}", index);
//                }
//            });

//            executorService.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    log.info("task: {}", index);
//                }
//            }, 3, TimeUnit.SECONDS);

            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            }, 3, 2, TimeUnit.SECONDS);
        }

//        executorService.shutdown();

    }
}
