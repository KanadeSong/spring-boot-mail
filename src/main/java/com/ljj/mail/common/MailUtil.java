package com.ljj.mail.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * MailUtil 异步发送
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:37
 */
@Slf4j
public class MailUtil {

    /**
     * 线程名称
     */
    ThreadFactory build = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

    private ScheduledExecutorService service = new ScheduledThreadPoolExecutor(6, build);

    private final AtomicInteger count = new AtomicInteger(1);

    public void start(final JavaMailSender mailSender, final SimpleMailMessage message) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (count.get() == 2) {
                        service.shutdown();
                        ;
                        log.info("the task is down");
                    }
                    log.info("start send email and the index is {}", count);
                    mailSender.send(message);
                    log.info("send email success");
                } catch (Exception e) {
                    log.error("send email fail", e);
                }
            }
        });
    }

    public void startHtml(final JavaMailSender mailSender, final MimeMessage message) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (count.get() == 2) {
                        service.shutdown();
                        log.info("the task is down");
                    }
                    log.info("start send email and the index is " + count);
                    mailSender.send(message);
                    log.info("send email success");
                } catch (Exception e) {
                    log.error("send email fail", e);
                }
            }
        });
    }

    /**
     * 获取 Sender 多实例发送
     *
     * @return
     */
    public static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.mxhichina.com");
        sender.setPort(25);
        sender.setUsername("");
        sender.setPassword("");
        sender.setDefaultEncoding("utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", 1000 + "");
        p.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(p);
        return sender;
    }


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("a");
        for (int i = 0; i < 10000; i++) {
            sb.append(i+" ");
            if (i%100 == 1){
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static String replace(String filename) {
        //.$|()[{^?*+\等特殊符号不在匹配中，需要转移符\
        return filename.substring(0, filename.lastIndexOf("."));
    }

}
