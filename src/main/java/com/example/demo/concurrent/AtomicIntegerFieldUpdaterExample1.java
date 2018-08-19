package com.example.demo.concurrent;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


@Slf4j
public class AtomicIntegerFieldUpdaterExample1 {
    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterExample1> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterExample1.class, "count");

    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterExample1 example1 = new AtomicIntegerFieldUpdaterExample1();
        if(updater.compareAndSet(example1, 100, 120)) {
            log.info("update success 1: {}", example1.getCount());
        } else {
            log.info("update fail 1: {}", example1.getCount());
        }

        if(updater.compareAndSet(example1, 100, 120)) {
            log.info("update success 2: {}", example1.getCount());
        } else {
            log.info("update fail 2: {}", example1.getCount());
        }
    }
}
