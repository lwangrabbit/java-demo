package com.example.demo.unsafeclass;

import com.example.demo.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class SimpleDateFormatExample1 {
    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception err) {
                    log.error("exception: {}", err);
                }
            });
        }
        exec.shutdown();
    }

    private static void update() {
        try {
            simpleDateFormat.parse("20180826");
        } catch (Exception err) {
            log.error("parse exception: {}", err);
        }
    }
}
