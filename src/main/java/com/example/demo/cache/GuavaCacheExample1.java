package com.example.demo.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import lombok.extern.slf4j.Slf4j;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GuavaCacheExample1 {
    public static void main(String[] args) {
        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10)        //最多存放10个数据
                .expireAfterWrite(10, TimeUnit.SECONDS)     //缓存10s
                .recordStats()      //开启记录状态数据功能
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return -1;
                    }
                });

        log.info("{}", cache.size());       //0

        log.info("{}", cache.getIfPresent("key1")); //null

        cache.put("key1", 1);
        log.info("{}", cache.getIfPresent("key1")); //1

        cache.invalidate("key1");
        log.info("{}", cache.getIfPresent("key1"));  //null

        log.info("{}", cache.size());       //0

        try {
            log.info("{}", cache.get("key2"));      //-1
            cache.put("key2", 2);
            log.info("{}", cache.get("key2"));      //2

            log.info("{}", cache.size());           //1

            for(int i = 3; i < 13; i++) {
                cache.put("key" + i, i);
            }

            log.info("{}", cache.size());       //10
            log.info("{}", cache.getIfPresent("key2"));     //null

            Thread.sleep(13*1000);

            log.info("{}", cache.get("key5"));     //-1

            log.info("{},{},{},{}", cache.stats().hitCount(), cache.stats().missCount(),
                    cache.stats().hitRate(), cache.stats().missRate());

        } catch (Exception err){
            log.info("exception", err);
        }


    }
}
