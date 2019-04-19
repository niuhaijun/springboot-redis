package com.niu.springbootredis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


public class RedisDataSourceImpl implements RedisDataSource {

  private static final Logger logger = LoggerFactory.getLogger(RedisDataSourceImpl.class);

  private ShardedJedisPool shardedJedisPool;

  @Override
  public ShardedJedis getRedisClient() {

    ShardedJedis shardJedis = null;
    try {
      shardJedis = shardedJedisPool.getResource();
      return shardJedis;
    } catch (Exception e) {
      logger.error("getRedisClient error", e);
      if (null != shardJedis) {
        shardJedis.close();
      }
    }
    return null;
  }

  @Override
  public void returnResource(ShardedJedis shardedJedis) {
    //shardedJedisPool.returnResource(shardedJedis);
    shardedJedis.close();
  }

  @Override
  public void returnResource(ShardedJedis shardedJedis, boolean broken) {

    if (broken) {
      //shardedJedisPool.returnBrokenResource(shardedJedis);
      shardedJedis.close();
    } else {
      //shardedJedisPool.returnResource(shardedJedis);
      shardedJedis.close();
    }
  }

  public ShardedJedisPool getShardedJedisPool() {

    return shardedJedisPool;
  }

  public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {

    this.shardedJedisPool = shardedJedisPool;
  }

}
