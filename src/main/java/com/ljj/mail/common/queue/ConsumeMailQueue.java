package com.ljj.mail.common.queue;

import com.ljj.mail.common.model.Email;
import com.ljj.mail.service.impl.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * ConsumeMailQueue 消费队列
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:35
 */
@Component
@Slf4j
public class ConsumeMailQueue {

    @Autowired
    private MailServiceImpl mailService;

    @PostConstruct
    public void startThread() {
        log.info("ConsumeMailQueue 邮件消费队列服务已启动！");
        // 两个大小的固定线程池
        ThreadPoolExecutor e = new ThreadPoolExecutor(
                2,
                2,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        e.submit(new PollMail(mailService));
        e.submit(new PollMail(mailService));
    }

    @PreDestroy
    public void stopThread() {
        log.info("ConsumeMailQueue 邮件消费队列服务已被销毁！");
    }

    class PollMail implements Runnable {

        MailServiceImpl mailService;

        public PollMail(MailServiceImpl mailService) {
            this.mailService = mailService;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Email mail = MailQueue.getMailQueue().consume();
                    if (mail != null) {
                        mailService.sendHtmlMail(mail);
                        log.info("剩余邮件总数：{}", MailQueue.getMailQueue().size());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
