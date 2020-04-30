package com.ljj.mail.common.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * SendMail 统计失败邮件定时重新发送
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:36
 */
@Component
@Slf4j

public class SendMailTask {
    //@Scheduled(cron = "0/6 * * * * *")
    public void sendMail() {
        //每6秒执行一次
        log.info("邮件重新发送");
    }
}
