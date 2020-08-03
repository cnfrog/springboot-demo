package cn.ypjalt.springboot;

import cn.ypjalt.springboot.bean.Employee;
import cn.ypjalt.springboot.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringBoot09CacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 操作k-v字符串

    @Autowired
    RedisTemplate redisTemplate; // k,v 都是对象的


    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    /**
     * string(字符串),list(列表),set(集合),hash(散列),ZSet(有序集合)
     * stringRedisTemplate.opsForValue() [string 字符串]
     * stringRedisTemplate.opsForList() [list(列表)]
     */

    @Test
    public void test01() {
        // 给 redis 中保存数据
//        stringRedisTemplate.opsForValue().append("msg", "hello");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(msg);
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
        stringRedisTemplate.opsForList().leftPush("mylist", "3");
    }

    @Test
    public void test2() {
        Employee empById = employeeMapper.getEmpById(1);
//        redisTemplate.opsForValue().set("emp-01", empById);
        // 1.将数据以 json 的方式保存
        // (1)自己将对象转换为 json
        // (2)redisTemplate默认的序列化规则,改变默认的序列化规则

        empRedisTemplate.opsForValue().set("emp-01", empById);

    }


    @Test
    void contextLoads() {
        Employee empById = employeeMapper.getEmpById(1);
        System.out.println(empById);
    }

}
