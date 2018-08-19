package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//用来标记线程安全的类或者写法
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)  //注解存在的范围
public @interface ThreadSafe {

    String value() default "";

}
