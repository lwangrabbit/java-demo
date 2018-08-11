package com.example.demo.Singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingletonExample1 {

    private SingletonExample1() {

    }

    private volatile static SingletonExample1 instance = null;

    //why volatile, prevent cpu reorder to 1-3-2
    //1. allocate memory
    //2. ctor Instance
    //3. instance = memory
    public static SingletonExample1 getInstance() {
        if(instance == null) {                              //B
            synchronized (SingletonExample1.class) {
                if(instance == null) {
                    instance = new SingletonExample1();     //A-3
                }
            }
        }
        return instance;
    }
}
