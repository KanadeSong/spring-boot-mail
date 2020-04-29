package com.ljj.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
        log.info("邮件服务启动!");

    }

}
