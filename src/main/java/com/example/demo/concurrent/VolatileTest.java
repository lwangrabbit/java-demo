package com.example.demo.concurrent;

import com.example.demo.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class VolatileTest {

    private static final int clientTotal = 5000;
    private static final int threadTotal = 200;

    private static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception err) {
                    log.error("exception:{}", err);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count: {}", count);
    }

    private static void add() {
        count++;
    }
}
