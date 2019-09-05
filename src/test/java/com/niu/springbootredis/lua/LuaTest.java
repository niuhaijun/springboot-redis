package com.niu.springbootredis.lua;

import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: niuhaijun
 * @Date: 2019-09-05 16:07
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LuaTest {

  @Autowired
  private StringRedisTemplate redisTemplate;


  @Test
  public void test() {

    DefaultRedisScript<Long> script = new DefaultRedisScript<>();
    script.setResultType(Long.class);
    script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/exists.lua")));

    String key = "mykey";
    String args0 = "5";
    Long res = redisTemplate.execute(script, Collections.singletonList(key), args0);
    System.out.println(res);
  }
}

