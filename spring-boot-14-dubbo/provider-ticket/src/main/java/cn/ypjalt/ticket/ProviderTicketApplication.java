package cn.ypjalt.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.将服务提供者注册到服务中心
 * 引入 dubbo 和 zkclient 相关依赖
 * 配置 dubbo 的扫描包和注册中心地址
 * 使用@DubboService 发布服务
 */
@SpringBootApplication
public class ProviderTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }

}
