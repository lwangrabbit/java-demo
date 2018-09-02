package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class NewCachedThreadPoolExample1 {

    private static int clientTotal = 10;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int index = 0; index < clientTotal; index++) {
            final int threadNum = index;
            Thread.sleep(10);
            exec.execute(() -> {
                try {
                    add(threadNum);
                } catch (Exception err) {
                    log.error("exception: {}", err);
                }
            });
        }
        log.info("main finish");
        exec.shutdown();
    }

    private static void add(int threadNum) {
        try {
            log.info("thread-{}", threadNum);
            Thread.sleep(60000);
        } catch (Exception err) {
            log.error("exception: {}", err);
        }
    }
}
