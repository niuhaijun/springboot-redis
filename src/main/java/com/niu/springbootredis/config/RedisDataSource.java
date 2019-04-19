package com.niu.springbootredis.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisDataSource {

  private static final Logger logger = LoggerFactory.getLogger(RedisDataSource.class);

  @Getter
  @Setter
  private ShardedJedisPool shardedJedisPool;

  public ShardedJedis getRedisClient() {

    try {
      return shardedJedisPool.getResource();
    } catch (Exception e) {
      logger.error("getRedisClient error", e);
    }
    return null;
  }

  public void returnResource(ShardedJedis shardedJedis) {

    shardedJedis.close();
  }
}
