package cn.ypjalt.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.引入 spring security 相关依赖
 * 2.编写 springSecurity 的配置类
 *
 * @EnableWebSecurity extends WebSecurityConfigurerAdapter
 * 3.控制请求的访问权限
 */

@SpringBootApplication
public class SpringBoot13SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot13SecurityApplication.class, args);
    }

}
