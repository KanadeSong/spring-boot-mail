package com.ljj.mail.service.impl;

import com.ljj.mail.common.dynamicquery.DynamicQuery;
import com.ljj.mail.common.model.Email;
import com.ljj.mail.common.model.Result;
import com.ljj.mail.common.queue.MailQueue;
import com.ljj.mail.common.util.Constants;
import com.ljj.mail.entity.OaEmail;
import com.ljj.mail.repository.MailRepository;
import com.ljj.mail.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * MailServiceImpl
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 1:49
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    static {
        System.setProperty("mail.mime.splitlongparameters", "false");
    }

    /**
     * 发送者
     */
    @Value("spring.mail.username")
    public String userName;

    /**
     * 发送者
     */
    @Value("server.path")
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
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void send(Email mail) throws Exception {
        log.info("发送文本邮件：{}", mail.getContent());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userName);
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailSender.send(message);

    }

    @Override
    public void sendHtml(Email mail) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //这里可以自定义发信名称
        helper.setFrom(userName, "LeeJack");
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        helper.setText("<html><body><img src=\"cid:springcloud\" ></body></html>",
                true);
        // 发送图片
        File file = ResourceUtils.getFile("classpath:static"
                + Constants.SF_FILE_SEPARATOR + "image"
                + Constants.SF_FILE_SEPARATOR + "springcloud.png");
        // 发送附件
        file = ResourceUtils.getFile("classpath:static"
                + Constants.SF_FILE_SEPARATOR + "file"
                + Constants.SF_FILE_SEPARATOR + "关注科帮网获取更多源码.zip");
        helper.addAttachment("LeeJack", file);
        mailSender.send(message);
    }

    @Override
    public void sendFreemarker(Email mail) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //这里可以自定义发信名称比如：爪哇笔记
        helper.setFrom(userName, "LeeJack");
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        model.put("path", path);
        Template template = configuration.getTemplate(mail.getTemplate());
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(text, true);
        mailSender.send(message);
        mail.setContent(text);
        OaEmail oaEmail = new OaEmail(mail);
        mailRepository.save(oaEmail);
    }

    /**
     * 弃用
     */
    @Override
    public void sendThymeleaf(Email mail) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(userName);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Context context = new Context();
        context.setVariable("email", mail);
        String text = templateEngine.process(mail.getTemplate(), context);
        helper.setText(text, true);
        mailSender.send(message);
    }

    @Override
    public void sendQueue(Email mail) throws Exception {
        MailQueue.getMailQueue().produce(mail);
    }

    @Override
    public void sendRedisQueue(Email mail) throws Exception {
        //redis发布订阅模式
        redisTemplate.convertAndSend("mail", mail);
    }

    @Override
    public Result listMail(Email mail) {
        List<OaEmail> list = mailRepository.findAll();
        return Result.ok(list);
    }
}
