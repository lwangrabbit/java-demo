package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockConditionExample1 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal");    //1
                condition.await();
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
            log.info("get signal");         //4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock");       //2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
            condition.signal();
            log.info("send signal ~");      //3
            reentrantLock.unlock();
        }).start();
    }
}
