package com.niu.springbootredis.pagerank;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: niuhaijun
 * @Date: 2019-08-25 15:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Account {

  // 名次
  private Long rank;

  // 钱包地址
  private String address;

  // 余额
  private BigDecimal balance;

  // 参与事务的总数
  private Long txCount;
}
