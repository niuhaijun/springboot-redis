package com.niu.springbootredis.pagerank;

import com.alibaba.fastjson.JSONObject;
import com.niu.springbootredis.config.RedisClientTemplate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Tuple;

/**
 * 排行榜
 *
 * @Author: niuhaijun
 * @Date: 2019-08-25 15:18
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageRankTest {

  @Autowired
  private RedisClientTemplate redisClientTemplate;

  @Test
  public void test() {

    List<Account> list = new ArrayList<>();

    list.add(new Account(null, "1", BigDecimal.valueOf(1), 1l));// 9
    list.add(new Account(null, "2", BigDecimal.valueOf(2), 1l));// 8
    list.add(new Account(null, "3", BigDecimal.valueOf(3), 1l));// 7
    list.add(new Account(null, "4", BigDecimal.valueOf(4), 1l));// 6
    list.add(new Account(null, "5", BigDecimal.valueOf(5), 1l));// 5
    list.add(new Account(null, "6", BigDecimal.valueOf(6), 1l));// 4
    list.add(new Account(null, "7", BigDecimal.valueOf(7), 1l));// 3
    list.add(new Account(null, "8", BigDecimal.valueOf(8), 1l));// 2
    list.add(new Account(null, "9", BigDecimal.valueOf(9), 1l));// 1

    String key = "ppcoin:paihangbang";
    String addressPre = key + ":address:";

    redisClientTemplate.del(key);

    System.out.println("元素总数 " + redisClientTemplate.zcard(key));

    int pageNo = 2;
    int pageSize = 3;

    int start = (pageNo - 1) * pageSize;
    int end = pageNo * pageSize - 1;

    /**
     * zadd key score member
     *
     * 初始化数据
     */
    for (Account account : list) {
      redisClientTemplate.zadd(key, account.getBalance().doubleValue(), account.getAddress());
      redisClientTemplate.set(addressPre + "" + account.getAddress(), "" + account.getTxCount());
    }

    System.out.println("分页查询， 逆序" + "start = " + start + "  end = " + end);

    // zrevrange key start end withscores
    Set<Tuple> set = redisClientTemplate.zrevrangeWithScores(key, start, end);
    for (Tuple tuple : set) {
      String address = tuple.getElement();
      double balance = tuple.getScore();

      // zrevrank key member 获取逆序排名， 从0开始
      Long rank = redisClientTemplate.zrevrank(key, address) + 1;
      String txCount = redisClientTemplate.get(addressPre + address);

      Account account = new Account();
      account.setRank(rank);
      account.setAddress(address);
      account.setBalance(BigDecimal.valueOf(balance));
      account.setTxCount(Long.valueOf(txCount));

      System.out.println(JSONObject.toJSONString(account));
    }
  }

}
