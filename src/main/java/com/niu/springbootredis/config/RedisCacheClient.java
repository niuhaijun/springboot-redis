package com.niu.springbootredis.config;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-19 12:56
 * @Version 1.0
 */
public class RedisCacheClient {

  private final static Logger logger = LoggerFactory.getLogger(RedisCacheClient.class);

  private JedisPool jedisPool;

  public void setJedisPool(JedisPool jedisPool) {

    this.jedisPool = jedisPool;
  }

  public void set(String key, Serializable value) {

    this.set(key, value, null);
  }

  public void set(String key, Serializable value, Integer expireSeconds) {

    Jedis jedis = jedisPool.getResource();
    try {
      jedis.set(key.getBytes(), SerializationUtils.serialize(value));
      if (expireSeconds != null) {
        jedis.expire(key.getBytes(), expireSeconds);
      }
    } finally {
      jedis.close();
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T get(String key) {

    Jedis jedis = jedisPool.getResource();
    try {
      byte[] value = jedis.get(key.getBytes());
      if (value != null) {
        return (T) SerializationUtils.deserialize(value);
      }
      return null;
    } finally {
      jedis.close();
    }
  }

  public void delete(String... keys) {

    Jedis jedis = jedisPool.getResource();
    try {
      jedis.del(keys);
    } finally {
      jedis.close();
    }
  }

  public long inCr(String key) {

    Jedis jedis = jedisPool.getResource();
    try {
      return jedis.incr(key);
    } finally {
      jedis.close();
    }
  }

  public long incrBy(String key, long num) {

    Jedis jedis = jedisPool.getResource();
    try {
      long number = jedis.incrBy(key, num);
      jedis.expire(key, 86400);
      return number;
    } finally {
      jedis.close();
    }
  }

  public long decr(String key) {

    Jedis jedis = jedisPool.getResource();
    try {
      return jedis.decr(key);
    } finally {
      jedis.close();
    }
  }

  public boolean exists(String key) {

    Jedis jedis = jedisPool.getResource();
    try {
      return jedis.exists(key.getBytes());
    } finally {
      jedis.close();
    }
  }

  public Set<String> keys(String pattern) {

    Jedis jedis = jedisPool.getResource();
    try {
      return jedis.keys(pattern);
    } finally {
      jedis.close();
    }
  }

  public long lLength(String key) {

    Jedis jedis = jedisPool.getResource();
    try {
      return jedis.llen(key);
    } finally {
      jedis.close();
    }
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> lRangeAll(String key) {

    Jedis jedis = jedisPool.getResource();
    try {
      List<T> relist = new ArrayList<T>();
      List<byte[]> list = jedis.lrange(key.getBytes(), 0, -1);
      for (byte[] b : list) {
        if (b != null) {
          relist.add((T) SerializationUtils.deserialize(b));
        }
      }
      return relist;
    } finally {
      jedis.close();
    }
  }

  public long lAdd(String key, Serializable value) {

    Jedis jedis = jedisPool.getResource();
    try {
      return jedis.lpush(key.getBytes(), SerializationUtils.serialize(value));
    } finally {
      jedis.close();
    }
  }

  public Long setStringIfNotExists(String key, String value) {

    Jedis jedis = jedisPool.getResource();
    try {
      long num = jedis.setnx(key, value);
      jedis.expire(key, 86400);
      return num;
    } finally {
      jedis.close();
    }
  }

  public long addSortSet(String key, Map<String, Double> map, int expireTime) {

    Jedis jedis = jedisPool.getResource();
    try {
      long result = jedis.zadd(key, map);
      jedis.expire(key, expireTime);
      return result;
    } finally {
      jedis.close();
    }
  }

  public Set<String> getSortSet(String key, long start, long end) {

    Jedis jedis = jedisPool.getResource();
    try {
      Set<String> set = jedis.zrevrange(key, start, end);
      return set;
    } finally {
      jedis.close();
    }
  }

  public long delSortSet(String key, String member) {

    Jedis jedis = jedisPool.getResource();
    try {
      long result = jedis.zrem(key, member);
      return result;
    } finally {
      jedis.close();
    }
  }

  public long delSortSet(String key) {

    Jedis jedis = jedisPool.getResource();
    try {
      long result = jedis.del(key);
      return result;
    } finally {
      jedis.close();
    }
  }

}

