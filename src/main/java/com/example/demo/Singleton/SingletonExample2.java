package com.example.demo.Singleton;

import com.example.demo.annotation.ThreadSafe;

/**
 * 饿汉模式
 * 类装载的时候创建
 * 如果没有使用，可能会造成资源的浪费
 * 如果私有构造函数有过多的处理操作，可能会导致类加载的时候特别慢
 */
@ThreadSafe
public class SingletonExample2 {
    //私有构造函数
    private SingletonExample2() {

    }

    private static SingletonExample2 instance = new SingletonExample2();

    public SingletonExample2 getInstance() {
        return instance;
    }
}
