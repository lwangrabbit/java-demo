package com.example.demo.publish;

import com.example.demo.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish publish = new UnsafePublish();

        log.info("{}", Arrays.toString(publish.getStates()));

        publish.getStates()[0] = "d";

        log.info("{}", Arrays.toString(publish.getStates()));
    }
}
