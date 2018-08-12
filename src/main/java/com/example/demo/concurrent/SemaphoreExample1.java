package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
class SemaphoreExample1 {

    private final static int threadCount = 200;

    private final static int useCount = 10;

    private final static Semaphore semaphore = new Semaphore(useCount);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test(threadNum);
                    semaphore.release();
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
    }
}
