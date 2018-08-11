package com.example.demo.Singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingletonExample1 {

    private SingletonExample1() {

    }

    private volatile static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance() {
        if(instance == null) {
            synchronized (SingletonExample1.class) {
                if(instance == null) {
                    instance = new SingletonExample1();
                }
            }
        }
        return instance;
    }
}
