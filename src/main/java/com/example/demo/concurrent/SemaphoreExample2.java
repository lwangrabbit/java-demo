package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
class SemaphoreExample2 {

    private final static int threadCount = 20;

    private final static int useCount = 3;

    private final static Semaphore semaphore = new Semaphore(useCount);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(3);
                    test(threadNum);
                    semaphore.release(3);
                } catch (Exception err) {
                    log.info("{}", err);
                }
            });
        }
        executorService.shutdown();
        log.info("finish");
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
