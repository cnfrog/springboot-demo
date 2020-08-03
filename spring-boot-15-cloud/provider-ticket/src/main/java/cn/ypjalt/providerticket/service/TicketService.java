package cn.ypjalt.providerticket.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    public String getTicket() {
        System.out.println("这是 8001");
        return "战狼";
    }
}
