package com.ljj.mail.controller;

import com.ljj.mail.common.model.Email;
import com.ljj.mail.common.model.Result;
import com.ljj.mail.service.impl.MailServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * MailController 邮件管理
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:32
 */
@Api(tags = "邮件管理")
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailServiceImpl mailService;

    @PostMapping("send")
    public Result send(Email mail) {
        try {
            mailService.sendFreemarker(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }

    /**
     * 邮件列表
     *
     * @param mail
     * @return
     */
    @PostMapping("list")
    public Result list(Email mail) {
        return mailService.listMail(mail);
    }

    /**
     * 队列测试
     * @param mail
     * @return
     */
    @PostMapping("queue")
    public Result queue(Email mail) {
        try {
            mailService.sendQueue(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }
}
