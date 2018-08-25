package com.example.demo.Singleton;

import com.example.demo.annotation.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用的时候初始化
 */
@NotThreadSafe
public class SingletonExample1 {
    private SingletonExample1() {

    }

    private static SingletonExample1 instance = null;

    public SingletonExample1 getInstance() {
        if(instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
