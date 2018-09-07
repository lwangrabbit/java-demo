package com.example.demo.failfast;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.lang.Thread;

@Slf4j
public class CopyOnWriteArrayListExample1 {

    public static void main(String[] args) throws Exception {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        arrayList.add("f");


        new Thread(() -> {
            Iterator iterator = arrayList.iterator();
            while(iterator.hasNext()) {
                try {
                    Thread.sleep(10);
                } catch (Exception err) {
                    log.error("err: {}", err);
                }
                log.info("{},", iterator.next());
            }
        }).start();

        new Thread(() -> {
            for (int index = 0; index < arrayList.size(); index++) {
                if(arrayList.get(index) == "d") {
                    arrayList.remove(index);
                    log.info("remove: element");
                }
            }
        }).start();

        Thread.sleep(3000);
    }
}
