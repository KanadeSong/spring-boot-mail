package com.ljj.mail.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * TemplateTests
 * 读取 1000 次效率 thymeleaf 567ms freemarker 116ms
 * 读取 10000 次效率 thymeleaf 1063ms freemarker 262ms
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/30 16:56
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class TemplateTests {
    //thymeleaf
    @Autowired
    private TemplateEngine templateEngine;

    //freemarker
    @Autowired
    private Configuration configuration;

    @Test
    public void thymeleafTest() {
        long startTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder("a");
        for (int i = 0; i < 10000; i++) {
            Context context = new Context();
            context.setVariable("project", "Spring Boot Demo");
            context.setVariable("author", "Yangkai.Shen");
            context.setVariable("url", "https://github.com/xkcoding/spring-boot-demo");

            sb.append(templateEngine.process("welcome", context));
        }

        long endTime = System.currentTimeMillis();
        log.info("thymeleaf读取1000次文件的效率为：{}ms", (endTime - startTime));
        log.info(sb.toString().substring(0, 1000));
    }

    @Test
    public void configurationTest() throws IOException, TemplateException {
        long startTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder("a");
        for (int i = 0; i < 10000; i++) {
            Map<String, Object> model = new HashMap<>(16);
            model.put("project", "Spring Boot Demo");
            model.put("author", "Yangkai.Shen");
            model.put("url", "https://github.com/xkcoding/spring-boot-demo");

            Template template = configuration.getTemplate("welcome.html");
            sb.append(FreeMarkerTemplateUtils.processTemplateIntoString(template, model));
        }

        long endTime = System.currentTimeMillis();
        log.info("freemarker读取1000次文件的效率为：{}ms", (endTime - startTime));
        log.info(sb.toString().substring(0, 1000));
    }
}
