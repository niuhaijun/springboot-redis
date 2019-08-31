package com.niu.springbootredis.pubsub;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @Author: niuhaijun
 * @Date: 2019-08-31 21:02
 * @Version 1.0
 */
@Service
public class WeatherServer {

  public void publishMessage(Jedis template, String channel, String msg) {

    template.publish(channel, msg);
  }
}

