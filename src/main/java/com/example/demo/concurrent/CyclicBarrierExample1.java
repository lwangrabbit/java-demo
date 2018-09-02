package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
class CyclicBarrierExample1 {

    private final static int threadCount = 10;

    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        log.info("begin to start");
    });

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception err) {
                    log.error("{}", err);
                }
            });
        }
        executorService.shutdown();
        log.info("finish");
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();
        log.info("{} is continue", threadNum);
    }
}
