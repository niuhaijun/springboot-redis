package com.niu.springbootredis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = JedisProperties.JEDIS_PREFIX)
@Data
public class JedisProperties {

  static final String JEDIS_PREFIX = "jedis";

  private String host;

  private int port;

  private int maxTotal;

  private int maxIdle;

  private int minIdle;

  private long maxWaitMillis;

  private int timeout;
}
