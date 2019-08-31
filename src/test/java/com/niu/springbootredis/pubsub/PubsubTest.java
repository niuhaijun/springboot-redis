package com.niu.springbootredis.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: niuhaijun
 * @Date: 2019-08-31 21:03
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PubsubTest {

  public static final String CHANNEL_NAME = "MyChannel";
  public static final String REDIS_HOST = "localhost";
  public static final int REDIS_PORT = 6379;

  private final static JedisPoolConfig POOL_CONFIG = new JedisPoolConfig();
  private final static JedisPool JEDIS_POOL = new JedisPool(POOL_CONFIG, REDIS_HOST, REDIS_PORT, 0);


  @Autowired
  private Farmer farmer;
  @Autowired
  private Worker worker;
  @Autowired
  private Programmer programmer;


  @Test
  public void pubsub() {

    /*订阅者redis客户端*/
    final Jedis farmerJedis = JEDIS_POOL.getResource();
    final Jedis workerJedis = JEDIS_POOL.getResource();
    final Jedis programmerJedis = JEDIS_POOL.getResource();

    /*发布者redis*/
    final Jedis publisherJedis = JEDIS_POOL.getResource();

    //订阅线程：接收消息，因为Jedis是以阻塞的方式等待发布者消息的，所以每个Jedis客户端必须对应一个独立的线程。不然只会有第一个Jedis接受到消息。
    new Thread(new Runnable() {
      public void run() {

        try {
          farmerJedis.subscribe(farmer, CHANNEL_NAME);
          log.info("Subscription ended.");
        }
        catch (Exception e) {
          log.error("Subscribing failed.", e);
        }
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {

        try {
          workerJedis.subscribe(worker, CHANNEL_NAME);
          log.info("Subscription ended.");
        }
        catch (Exception e) {
          log.error("Subscribing failed.", e);
        }
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {

        try {
          programmerJedis.subscribe(programmer, CHANNEL_NAME);
          log.info("Subscription ended.");
        }
        catch (Exception e) {
          log.error("Subscribing failed.", e);
        }
      }
    }).start();

    //主线程：发布消息到CHANNEL_NAME频道上
    WeatherServer weatherServer = new WeatherServer();
    weatherServer.publishMessage(publisherJedis, CHANNEL_NAME, "rain");

    farmerJedis.close();
    workerJedis.close();
    programmerJedis.close();
  }
}




