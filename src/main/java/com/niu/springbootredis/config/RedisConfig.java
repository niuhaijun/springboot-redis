package com.niu.springbootredis.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
@EnableConfigurationProperties({JedisProperties.class})
public class RedisConfig {

  @Autowired
  private JedisProperties properties;

  @Bean
  public RedisClientTemplate redisClientTemplate() {

    RedisClientTemplate redisClientTemplate = new RedisClientTemplate();
    redisClientTemplate.setRedisDataSource(redisDataSource());
    return redisClientTemplate;
  }

  @Bean
  public RedisDataSource redisDataSource() {

    RedisDataSourceImpl redisDataSource = new RedisDataSourceImpl();
    redisDataSource.setShardedJedisPool(shardedJedisPool());
    return redisDataSource;
  }

  @Bean
  public ShardedJedisPool shardedJedisPool() {

    List<JedisShardInfo> shards = new ArrayList<>();
    shards.add(
        new JedisShardInfo(properties.getHost(), properties.getPort(), properties.getTimeout()));
    return new ShardedJedisPool(jedisPoolConfig(), shards);
  }

  @Bean
  public JedisPoolConfig jedisPoolConfig() {

    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(properties.getMaxTotal());
    config.setMaxIdle(properties.getMaxIdle());
    config.setMinIdle(properties.getMinIdle());
    config.setMaxWaitMillis(properties.getMaxWaitMillis());
    config.setTestOnBorrow(true);
    config.setTestOnReturn(true);
    return config;
  }
}
