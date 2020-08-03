package cn.ypjalt.user.service;

import cn.ypjalt.ticket.service.TicketService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @DubboReference
    TicketService ticketService;

    public void hello() {
        String ticket = ticketService.getTicket();
        System.out.println("买到票了" + ticket);
    }

}
