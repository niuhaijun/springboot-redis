# Redis 与 Memcached的区别
两者都是非关系型、内存、键值对数据库，主要有以下5个区别
* 1、数据类型
Redis支持五种数据类型，Memcached只支持字符串类型
* 2、持久化
Redis支持RDB和AOF两种持久化功能，Memcached不支持持久化
* 3、集群支持
Redis Cluster实现了分布式的支持，Memcached不支持分布式
* 4、内存管理机制
  * 在 Redis 中，并不是所有数据都一直存储在内存中，可以将一些很久没用的 value 交换到
    磁盘，而 Memcached 的数据则会一直在内存中。
  * Memcached 将内存分割成特定长度的块来存储数据，以完全解决内存碎片的问题。但是
    这种方式会使得内存的利用率不高，例如块的大小为 128 bytes，只存储 100 bytes 的数
    据，那么剩下的 28 bytes 就浪费掉了。
