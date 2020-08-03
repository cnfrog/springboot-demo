package cn.yppjalt.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringBoot12TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot12TaskApplication.class, args);
    }

}
