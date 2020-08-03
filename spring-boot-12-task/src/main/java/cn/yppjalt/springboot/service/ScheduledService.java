package cn.yppjalt.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    // 秒 分 时 日 月 周几
    //    0,1,2,3,4 * * * * MON-SAT
    //    0-4 * * * * MON-SAT
    //    0-4 * * * * 1-6
    //    0/4 * * * * MON-SAT 从 0 秒启动,每 4 秒启动一次
    // [0 0/5 14,18 * * ?] 每天 14 点和 18 点整,每隔五分钟执行一次
    // [0 15 10 ? * 0-6] 每个月的周一到周六10:15 执行一次
    // [0 0 2 ? * 6L] 每个月的最后一个周六 2 点执行一次
    // [0 0 2 LW * ?] 每个月的最后一个工作日凌晨 2 点执行一次
    // [0 0 2-4 ? * 1#1] 每个月的第一个周一凌晨 2 点到 4 点期间,每个整点执行一次
    @Scheduled(cron = "0 * * * * *")
    public void hello() {
        System.out.println("hello");
    }
}
