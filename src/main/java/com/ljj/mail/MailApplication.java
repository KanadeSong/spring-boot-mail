package com.ljj.mail;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * <p>
 * MailApplication 邮件服务系统启动类
 * </p>
 *
 * @author LeeJack
 */
@SpringBootApplication
@EnableDubbo
@EnableScheduling
@Slf4j
public class MailApplication {

    public static void main(String[] args) {

        SpringApplication.run(MailApplication.class, args);
        log.info("邮件服务启动!");
    }

}
