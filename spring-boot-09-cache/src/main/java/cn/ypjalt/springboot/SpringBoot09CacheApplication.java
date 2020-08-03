package cn.ypjalt.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一.搭建基本环境
 * 1.导入数据库文件
 * 2.创建 javaBean 文件
 * 3.整合 MyBatis 操作数据库
 * 1.配置数据源信息
 * 2.使用注解版的 MyBatis
 * 1).@MapperScan 指定扫描的 mapper 接口所在的包
 * 二.快速体验缓存
 * 步骤
 * 1.开启基于注解的缓存@EnableCaching
 * 2.标准缓存注解即可
 *
 * @Cacheable
 * @CachePut
 * @Caching 默认使用的是 ConcurrentMapCacheManager => ConcurrentMapCache 将数据保存在ConcurrentMap<Object, Object>
 * 开发中使用缓存中间件 redis,EhCache
 * 三.整合 redis 配置缓存
 * 引入 redis starter
 * 配置 redis
 * 测试缓存
 * 原理:CacheManager = cache 缓存组件来实际给缓存中存取数据
 * 1)引入 redis 的 starter,容器中保存的是 Rediscachemanager
 * 2)Redismanager帮我创建 redisCache 来作为缓存组件,RedisCache 通过操作 redis 来缓存数据
 * 3)默认保存数据 k-v 都是 object,利用序列化保存,如何保存为 json?
 * 1.引入 redis 的 starter cacheManager 变为 RedisCacheManager
 * 2.默认创建的RedisCacheManager操作 redis 的时候使用的是 RedisTemplate<Object, Object>
 * 3. RedisTemplate<Object, Object> 是默认使用 jdk 的序列化机制
 * 4)自定义 CacheManager
 */
@MapperScan("cn.ypjalt.springboot.mapper")
@SpringBootApplication
@EnableCaching
public class SpringBoot09CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot09CacheApplication.class, args);
    }

}
