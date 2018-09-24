package com.example.demo.Integer;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class IntegerExample1 {

    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;

        log.info("i1==i2:" + (i1 == i2));   //true

        Integer i3 = 500;
        Integer i4 = 500;

        log.info("i3==i4:" + (i3 == i4));   //false

    }
}
