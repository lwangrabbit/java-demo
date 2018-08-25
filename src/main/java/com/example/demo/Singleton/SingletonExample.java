package com.example.demo.Singleton;

import com.example.demo.annotation.Recommend;
import com.example.demo.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式  --> 双重同步锁单例模式
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonExample {

    private SingletonExample() {

    }

    private volatile static SingletonExample instance = null;

    //why volatile, prevent cpu reorder to 1-3-2
    //1. allocate memory
    //2. ctor Instance
    //3. instance = memory
    public static SingletonExample getInstance() {
        if(instance == null) {                              //B
            synchronized (SingletonExample.class) {
                if(instance == null) {
                    instance = new SingletonExample();     //A-3
                }
            }
        }
        return instance;
    }
}
