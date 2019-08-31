package com.niu.springbootredis.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPubSub;

/**
 * @Author: niuhaijun
 * @Date: 2019-08-31 20:59
 * @Version 1.0
 */
@Service
@Slf4j
public class Programmer extends JedisPubSub {

  @Override
  public void onMessage(String channel, String message) {

    log.info(String.format("client: PROGRAMMER. Message. Channel: %s, Msg: %s", channel, message));
    if ("rain".equals(message)) {
      log.info("PROGRAMMER : a satisfied day!!!");
    }
    else if ("sunny".equals(message)) {
      log.info("PROGRAMMER : a terrible day!!!");
    }
    else {
      log.info("PROGRAMMER : Spam messages");
    }
  }

}