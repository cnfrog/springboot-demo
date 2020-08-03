package cn.ypjalt.springboot02config;

import cn.ypjalt.springboot02config.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * springboot单元测试
 * 可以在测试期间很方便的类似编码一样进行自动注入容器的功能
 */
@SpringBootTest
class SpringBoot02ConfigApplicationTests {
    @Autowired
    Person person;
    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService() {
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
