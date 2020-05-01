package com.ljj.mail.dubbo.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.ljj.mail.common.dynamicquery.DynamicQuery;
import com.ljj.mail.dubbo.model.Email;
import com.ljj.mail.dubbo.model.Result;
import com.ljj.mail.common.queue.MailQueue;
import com.ljj.mail.entity.OaEmail;
import com.ljj.mail.repository.MailRepository;
import com.ljj.mail.dubbo.service.MailService;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * <p>
 * MailServiceImpl
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 1:49
 */
@Service(version = "1.0.0")
@Slf4j
public class MailServiceImpl implements MailService {


//    static {
//        System.setProperty("mail.mime.splitlongparameters", "false");
//    }

    /**
     * 发送者
     */
    @Value("${spring.mail.username}")
    public String from;

    /**
     * 发送者
     */
    @Value("${server.path}")
    public String path;

    @Autowired
    private DynamicQuery dynamicQuery;

    @Autowired
    private MailRepository mailRepository;

    /**
     * 执行者
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * freemarker
     */
    @Autowired
    private Configuration configuration;

    /**
     * thymeleaf
     */
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void sendSimpleMail(Email mail) {
        try {
            Thread.sleep(20*1000);
            log.info("当前{}线程休眠了20s",Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());

        String[] cc = mail.getCc();
        if (ArrayUtil.isNotEmpty(cc)) {
            message.setCc(cc);
        }
        mailSender.send(message);

        OaEmail oaEmail = new OaEmail(mail);
        mailRepository.save(oaEmail);

        log.info("发送人邮箱：{} 发出了文本邮件：{}", from, mail.getContent());
    }

    @Override
    public void sendHtmlMail(Email mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent(), true);

        String[] cc = mail.getCc();
        if (ArrayUtil.isNotEmpty(cc)) {
            helper.setCc(cc);
        }
        mailSender.send(message);

        OaEmail oaEmail = new OaEmail(mail);
        mailRepository.save(oaEmail);


        log.info("发送人邮箱：{} 发出了Html邮件：{}", from, mail.getContent());

    }

    @Override
    public void sendAttachmentMail(Email mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent(), true);

        String[] cc = mail.getCc();
        if (ArrayUtil.isNotEmpty(cc)) {
            helper.setCc(cc);
        }

        String[] filePath = mail.getAttachment();
        if (filePath != null) {
            for (int i = 0; i < filePath.length; i++) {
                FileSystemResource res = new FileSystemResource(new File(filePath[i]));
                helper.addAttachment(res.getFilename(), res);
            }
        }
        mailSender.send(message);

        OaEmail oaEmail = new OaEmail(mail);
        mailRepository.save(oaEmail);


        log.info("发送人邮箱：{} 发出带附件的邮件：{}", mail.getContent(), from);
    }

    @Override
    public void sendResourceMail(Email mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent(), true);

        String[] cc = mail.getCc();
        if (ArrayUtil.isNotEmpty(cc)) {
            helper.setCc(cc);
        }

        String[] filePath = mail.getAttachment();
        if (filePath != null) {
            for (int i = 0; i < filePath.length; i++) {
                FileSystemResource res = new FileSystemResource(new File(filePath[i]));
                helper.addInline(res.getFilename(), res);
            }
        }
        mailSender.send(message);

        OaEmail oaEmail = new OaEmail(mail);
        mailRepository.save(oaEmail);


        log.info("发送人邮箱：{} 发出静态资源的邮件：{}", mail.getContent(), from);
    }

    @Override
    public void sendQueue(Email mail) throws MessagingException, InterruptedException {
        MailQueue.getMailQueue().produce(mail);
    }

    @Override
    public void sendRedisQueue(Email mail) throws MessagingException {
        //redis发布订阅模式
        redisTemplate.convertAndSend("mail", mail);
        log.info("发送到 redis消息队列 mail:{}", mail);
    }

    @Override
    public Result listMail(Email mail) {
        List<OaEmail> list = mailRepository.findAll();
        return Result.ok(list);
    }


}
