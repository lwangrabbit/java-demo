package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    //修饰一个代码块
    public void test1() {
        synchronized(this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    public void test1_2() {
        synchronized(this.getClass()) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    //修改一个方法
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test1 - {}", i);
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();

        exec.execute(() -> {
            example1.test1_2();
        });

        exec.execute(() -> {
            example2.test1_2();
        });
    }
}
