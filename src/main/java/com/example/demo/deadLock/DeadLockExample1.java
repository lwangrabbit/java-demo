package com.example.demo.deadLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLockExample1 implements Runnable {

    public int flag = 1;

    private static Object o1 = new Object();
    private static Object o2 = new Object();


    @Override
    public void run() {
        log.info("flag: {}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(1000);
                } catch (Exception err) {
                    err.printStackTrace();
                }
                synchronized (o2) {
                    log.info("1");
                }
            }
        }
        if(flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(1000);
                } catch (Exception err) {
                    err.printStackTrace();
                }
                synchronized (o1) {
                    log.info("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockExample1 dl1 = new DeadLockExample1();
        DeadLockExample1 dl2 = new DeadLockExample1();

        dl1.flag = 0;
        dl1.flag = 1;

        new Thread(dl1).start();
        new Thread(dl2).start();
    }
}
