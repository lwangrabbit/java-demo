package com.example.demo.syncContainer;

import com.example.demo.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

@Slf4j
@NotThreadSafe
public class VectorExample1 {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();

        while(true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread t1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread t2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };

            t1.start();
            t2.start();
        }

    }

}
