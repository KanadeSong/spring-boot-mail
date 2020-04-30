package com.ljj.mail.service.impl;

import cn.hutool.core.io.resource.ResourceUtil;
import com.ljj.mail.MailApplicationTests;
import com.ljj.mail.common.model.Email;
import com.ljj.mail.common.model.Result;
import com.ljj.mail.common.queue.ConsumeMailQueue;
import com.ljj.mail.entity.OaEmail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * MailServiceImplTest
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 20:56
 */
@Slf4j
@SpringBootTest
public class MailServiceImplTest extends MailApplicationTests {

    @Autowired
    private MailServiceImpl mailService;

    // thymeleaf 模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    // freemarker 模板配置
    @Autowired
    private Configuration configuration;

    @Autowired
    private ConsumeMailQueue consumeMailQueue;

    @Test
    public void sendSimpleMail() {
        Email mail = new Email();
        mail.setEmail(new String[]{"545430154@qq.com"});
        mail.setSubject("这是一封文本邮件！");
        mail.setContent("ceshi");
        mailService.sendSimpleMail(mail);
    }

    @Test
    public void sendHtml() throws MessagingException, IOException, TemplateException {
        Email mail = new Email();
        mail.setEmail(new String[]{"545430154@qq.com"});
        mail.setSubject("这是一封 freemarker模板 邮件！");

        Map<String, Object> model = new HashMap<>(16);
        model.put("project", "Spring Boot Demo");
        model.put("author", "Yangkai.Shen");
        model.put("url", "https://github.com/xkcoding/spring-boot-demo");
        Template template = configuration.getTemplate("welcome.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        log.info(html);
        mail.setContent(html);

        mailService.sendHtmlMail(mail);
    }

    @Test
    public void sendAttachmentMail() throws Exception {
        Context context = new Context();
        context.setVariable("project", "Spring Boot Demo");
        context.setVariable("author", "Yangkai.Shen");
        context.setVariable("url", "https://github.com/xkcoding/spring-boot-demo");
        String emailTemplate = templateEngine.process("welcome", context);

        Email mail = new Email();
        mail.setEmail(new String[]{"545430154@qq.com"});
        mail.setSubject("ceshi");
        //mail.setContent("这是一封带附件的Html（富文本）邮件！");
        mail.setContent(emailTemplate);

        // 发送附件
        URL resource = ResourceUtil.getResource("static/file/关注科帮网获取更多源码.zip");
        String text = "<html><body><h3><p style=\"color:#4464FF\">附件：关注科帮网获取更多源码</p></h3></body></html>";

        // toURI()解决路径中文乱码问题
        mail.setAttachment(new String[]{resource.toURI().getPath(), resource.toURI().getPath()});

        log.info(resource.toURI().getPath());
        mailService.sendAttachmentMail(mail);
    }

    @Test
    public void sendResourceMail() throws Exception {
        Email mail = new Email();
        mail.setEmail(new String[]{"545430154@qq.com"});
        mail.setContent("这是一封带静态资源的Html（富文本）邮件！");
        mail.setSubject("ceshi");


        // 发送图片 cid:
        String text = "<html><body><img src=\'cid:springcloud.png\'><img src=\'cid:springcloud.png\'></body></html>";
        URL resource = ResourceUtil.getResource("static/image/springcloud.png");

        // toURI()解决路径中文乱码问题
        mail.setContent(text);
        mail.setAttachment(new String[]{resource.toURI().getPath(), resource.toURI().getPath()});

        mailService.sendResourceMail(mail);
    }


    @Test
    public void sendQueue() throws MessagingException, InterruptedException {
        for (int i = 0; i < 300; i++) {
            Email mail = new Email();
            mail.setEmail(new String[]{"545430154@qq.com"});
            mail.setContent("这是一封带静态资源的Html（富文本）邮件！" + i);
            mail.setSubject("ceshi" + i);
            mailService.sendQueue(mail);
        }

        for (; ; ) ;

    }

    @Test
    public void sendRedisQueue() throws MessagingException {
        for (int i = 0; i < 200; i++) {
            Email mail = new Email();
            mail.setEmail(new String[]{"545430154@qq.com"});
            mail.setContent("这是一封普通的文本邮件！" + i);
            mail.setSubject("ceshi" + i);
            mailService.sendRedisQueue(mail);
        }

        for (; ; ) ;
    }

    @Test
    public void listMail() {
        Result result = mailService.listMail(new Email());
        List<OaEmail> emails = (List<OaEmail>) result.get("msg");
        for (OaEmail email : emails) {
            log.info(email.toString());
        }
    }
}