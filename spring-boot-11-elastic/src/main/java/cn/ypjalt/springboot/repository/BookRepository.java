package cn.ypjalt.springboot.repository;

import cn.ypjalt.springboot.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
    public List<Book> findByBookNameLike(String name);
}
