package com.ljj.mail.redis;

import com.ljj.mail.dubbo.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * RedisTests
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/30 19:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redis() {
        Email mail = new Email();
        mail.setEmail(new String[]{"545430154@qq.com"});
        mail.setContent("这是一封带静态资源的Html（富文本）邮件！");
        mail.setSubject("ceshi");

        ValueOperations vos = redisTemplate.opsForValue();
        vos.set("mail", mail);

        mail = (Email) vos.get("mail");
        log.info(mail.toString());
    }
}
