package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

@Slf4j
public class StampedLockExample1 {
    //request total
    public static int clientTotal = 5000;

    //bingfa thread
    public static int threadTotal = 200;

    public static int count = 0;

    private final static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception err) {
                    log.error("exception: {}", err);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("finish");
    }

    private static void add() {
        long stamp = lock.writeLock();
        try {
            count++;
            log.info("{}", count);
        } finally {
            lock.unlock(stamp);
        }
    }

}
