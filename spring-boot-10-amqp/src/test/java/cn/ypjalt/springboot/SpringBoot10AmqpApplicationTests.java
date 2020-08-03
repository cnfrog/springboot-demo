package cn.ypjalt.springboot;

import cn.ypjalt.springboot.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringBoot10AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;


    @Test
    public void creatExchange() {
//        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//        System.out.println("创建完成");
//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
        // 创建绑定规则
        amqpAdmin.declareBinding(new Binding(
                "amqpadmin.queue",
                Binding.DestinationType.QUEUE,
                "amqpAdmin.exchange",
                "amqp.haha",
                null
        ));
    }


    /**
     * 1.单播(点对点)
     */
    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("hello", 123, true));
        // 对象默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "apple.new",
                new Book("西游记", "吴晨恩")
        );
    }

    //接收数据
    @Test
    public void test() {
        Object o = rabbitTemplate.receiveAndConvert("apple.new");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend(
                "exchange.fanout",
                "",
                new Book("西游记1", "吴晨1恩")
        );
    }


}
