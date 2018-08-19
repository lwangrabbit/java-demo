package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicIntegerCountExample1 {
    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception err) {
                    log.error("exception: {}", err);
                }
            });
        }
        log.info("count: {}", atomicInteger.get());
        exec.shutdown();
    }

    private static void add() {
        atomicInteger.getAndIncrement();
    }
}
