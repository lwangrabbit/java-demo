package com.example.demo.unsafeclass;

import com.example.demo.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class StringBufferExample1 {

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static StringBuffer stringBuffer = new StringBuffer();

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
        log.info("size: {}", stringBuffer.length());
        exec.shutdown();
    }

    private static void update() {
        stringBuffer.append("i");
    }
}
