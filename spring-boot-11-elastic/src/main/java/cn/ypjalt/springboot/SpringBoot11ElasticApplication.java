package cn.ypjalt.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * SpringBoot 默认支持两种技术和 ES 交互
 * 1.jtest
 * 2.SpringData ElasticSearc
 * 1).client 节点信息 clusterNodes,clusterName
 * 2).ElasticsearchRestTemplate操作 es
 * 3).编写一个ElasticsearchRepository 的子接口来操作 es
 */

@SpringBootApplication
public class SpringBoot11ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot11ElasticApplication.class, args);
    }

}
