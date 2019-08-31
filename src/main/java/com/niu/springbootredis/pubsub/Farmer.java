package com.niu.springbootredis.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPubSub;

/**
 * @Author: niuhaijun
 * @Date: 2019-08-31 20:57
 * @Version 1.0
 */
@Service
@Slf4j
public class Farmer extends JedisPubSub {

  @Override
  public void onMessage(String channel, String message) {

    log.info(String.format("client: FARMER. Message. Channel: %s, Msg: %s", channel, message));
    if ("rain".equals(message)) {
      log.info("FARMER : a satisfied day!!!");
    }
    else if ("sunny".equals(message)) {
      log.info("FARMER : a terrible day!!!");
    }
    else {
      log.info("FARMER : Spam messages");
    }
  }
}







