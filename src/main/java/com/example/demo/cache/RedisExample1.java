package com.example.demo.cache;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
public class RedisExample1 {

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(10);

        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set("name", "test1234");
            log.info("{}", jedis.get("name"));
            log.info("{}", jedis.get("name2"));
        } catch (Exception err) {
            log.error("exception", err);
        } finally {
            if(jedis != null) jedis.close();
            if(pool != null) pool.close();
        }
    }
}
