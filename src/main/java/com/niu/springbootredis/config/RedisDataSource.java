package com.niu.springbootredis.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Slf4j
public class RedisDataSource {

  @Getter
  @Setter
  private ShardedJedisPool shardedJedisPool;

  public ShardedJedis getRedisClient() {

    try {
      return shardedJedisPool.getResource();
    }
    catch (Exception e) {
      log.error("getRedisClient error, {}", e.getMessage());
    }
    return null;
  }

  public void returnResource(ShardedJedis shardedJedis) {

    shardedJedis.close();
  }
}
