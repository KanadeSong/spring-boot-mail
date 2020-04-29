package com.ljj.mail.common.util;


import com.ljj.MailApplicationTests;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;


public class MailUtilTest extends MailApplicationTests {

    @Test
    public void createMailSender() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin@52itstyle.com");
        message.setTo("345849402@qq.com");
        message.setSubject("测试");
        message.setText("测试");
        MailUtil.createMailSender().send(message);
    }
}
