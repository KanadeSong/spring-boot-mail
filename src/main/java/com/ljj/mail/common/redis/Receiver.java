package com.ljj.mail.common.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.mail.common.model.Email;
import com.ljj.mail.service.impl.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * Receiver
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:35
 */
@Slf4j
public class Receiver {

    private MailServiceImpl mailService;

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        log.info("接收redis的email消息<{}>", message);
        if (message == null) {
            log.info("接收redis消息<null>");
        } else {
            ObjectMapper mapper = new ObjectMapper();

            try {
                Email email = mapper.readValue(message, Email.class);
                mailService.send(email);
                log.info("接收email消息内容<{}>", email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }
}
