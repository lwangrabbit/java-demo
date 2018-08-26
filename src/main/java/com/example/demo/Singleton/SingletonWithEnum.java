package com.example.demo.Singleton;

import com.example.demo.annotation.Recommend;
import com.example.demo.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;


/**
 * 枚举模式，最安全
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonWithEnum {

    private SingletonWithEnum() {

    }

    public static SingletonWithEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonWithEnum singleton = null;

        //JVM保证这个方法绝对只被调用一次
        Singleton() {
            singleton = new SingletonWithEnum();
        }

        public SingletonWithEnum getInstance() {
            return singleton;
        }
    }
}
