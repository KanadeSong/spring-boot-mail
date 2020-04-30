package com.ljj.mail;

import com.ljj.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class MailApplication {

    @Autowired
    private MailService mailService;

    public static void main(String[] args) {

        SpringApplication.run(MailApplication.class, args);
        log.info("邮件服务启动!");
    }

}
