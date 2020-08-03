package cn.ypjalt.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@Component
@DubboService // 将服务发布出去
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "我不是药神!";
    }
}
