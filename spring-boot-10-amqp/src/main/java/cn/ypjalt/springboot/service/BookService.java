package cn.ypjalt.springboot.service;

import cn.ypjalt.springboot.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @RabbitListener(queues = {"news"})
    public void receive(Book book) {
        System.out.println("收到消息" + book);
    }

    @RabbitListener(queues = "atguigu")
    public void rec(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());

    }
}
