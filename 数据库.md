## 1、数据库
* 数据库主要由`dict字典`和`expires字典`构成
  * `dict`字典负责保存键值对
  * `expires`字典负责保存键的过期时间
  * 键空间的键和过期字典的键都指向同一个键对象
* 查看redis信息：`info`
* 删除key的过期时间: `persist`
* 设置key的生存时间：`expire`，`pexpire`
* 设置key的过期时间：`expireat`，`pexpireat`
* 获取key的剩余生存时间：`ttl`，`pttl`
---
