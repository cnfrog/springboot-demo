package cn.ypjalt.springboot;

import cn.ypjalt.springboot.bean.Book;
import cn.ypjalt.springboot.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@SpringBootTest
class SpringBoot11ElasticApplicationTests {
    @Autowired
    BookRepository bookRepository;

    @Test
    void contextLoads() {
        bookRepository.save(new Book(1, "西游记", "吴承恩"));
    }

}
