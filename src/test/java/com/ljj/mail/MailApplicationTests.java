package com.ljj.mail;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MailApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void contextLoads() {
    }

    /**
     * 生成加密密码
     */
    @Test
    public void testGeneratePassword() {
        //需要加密的密码
        String password = "password";
        //加密后的密码
        String encryptPassword = encryptor.encrypt(password);
        //解密为原来的密码
        String decryptPassword = encryptor.decrypt(encryptPassword);

        log.info("password = {}", password);
        log.info("encryptPassword = {}", encryptPassword);
        log.info("decryptPassword = {}", decryptPassword);
    }

}
