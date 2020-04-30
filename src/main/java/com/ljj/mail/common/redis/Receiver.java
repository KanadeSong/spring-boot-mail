package com.ljj.mail.common.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.mail.common.model.Email;
import com.ljj.mail.service.impl.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * Receiver 接收redis队列传来的消息
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:35
 */
@Slf4j
public class Receiver {

    @Autowired
    private MailServiceImpl mailService;

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        //锁 receiveMessage
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        log.info("接收到redis队列的邮件消息,开始进行验证！============================》");
        if (message == null) {
            log.info("邮件内容为空，放弃发送！");
        } else {
            ObjectMapper mapper = new ObjectMapper();

            try {
                Email email = mapper.readValue(message, Email.class);
                mailService.sendHtmlMail(email);
                String content = email.getContent();
                log.info("发送邮件完成!!latch:{} 邮件内容为：{}", latch, content.length() < 200 ? content :
                        content.substring(0, 200));
            } catch (Exception e) {
                e.printStackTrace();
                log.info("邮件发送出现异常，请联系管理员！");
            }
        }
        latch.countDown();
    }
}
