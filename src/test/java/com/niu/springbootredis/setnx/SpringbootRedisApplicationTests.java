package com.niu.springbootredis.setnx;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetnxTest {

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Test
  public void contextLoads() {

    Boolean isSuccess0 = redisTemplate.opsForValue().setIfAbsent("abc",
        "" + 100, 1, TimeUnit.HOURS);
    Boolean isSuccess1 = redisTemplate.opsForValue().setIfAbsent("abc",
        "" + 101, 1, TimeUnit.HOURS);

    System.out.println(isSuccess0);
    System.out.println(isSuccess1);
  }

}
