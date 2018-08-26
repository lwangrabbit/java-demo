package com.example.demo.unsafeclass;

import com.example.demo.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class JodaTimeExample1 {
    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            final int i = index;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    update(i);
                    semaphore.release();
                } catch (Exception err) {
                    log.error("exception: {}", err);
                }
            });
        }
        exec.shutdown();
    }

    private static void update(int i) {
        log.info("{} {}", i, DateTime.parse("20180826", dateTimeFormatter).toDate());
    }
}
