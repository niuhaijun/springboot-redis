# SpringBoot整合Redis

## 1、Redis中的5中对象类型
* string 字符串
* list 列表
* set 集合
* zset (sorted set) 有序集合
* hash 哈希
##2、对象类型适用的场景
* string
  * 计数器，登录次数限制
  * 存储数据库特定字段或JSON对象
  * 分布式锁
  * 常用的命令
    * set、setnx、getset、get
    * incr、incrby
    * decr、decrby
    * [string命令文档](https://www.redis.net.cn/order/3552.html)
* list
  * 队列，任务队列
  * 常用的命令
    * lpush、rpush
    * lpop、rpop
    * lrange、llen
    * [list命令文档](https://www.redis.net.cn/order/3577.html)
* set
  * 集合操作，交并补
  * 元素自动去重，无序
  * 常用命令
    * sadd、srem
    * sinter、sinterstore
    * sunion、sunionstore
    * sdiff、sdiffstore
    * [set命令文档](https://www.redis.net.cn/order/3594.html)
* zset
  * 排行榜，微博热搜
  * 常用命令
    * zadd、zrem
    * zrange、zrevrange
    * zrank、zrevrank
    * zcard、zcount
    * [zset命令文档](https://www.redis.net.cn/order/3609.html)
* hash 
  * 存储需要部分变更的数据结构，如用户信息等
  * 常用命令
    * hset、hmset
    * hget、hmget、hgetall
    * hdel、hlen
    * [hash命令文档](https://www.redis.net.cn/order/3564.html)