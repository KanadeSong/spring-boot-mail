package com.ljj.mail.service;

import com.ljj.mail.common.model.Email;
import com.ljj.mail.common.model.Result;

/**
 * <p>
 * MailService
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:31
 */
public interface MailService {
    /**
     * 纯文本邮件
     * @param mail
     * @throws Exception
     */
    void send(Email mail) throws Exception;

    /**
     * 富文本邮件
     * @param mail
     * @throws Exception
     */
    void sendHtml(Email mail) throws Exception;

    /**
     * 模版发送 freemarker
     * @param mail
     * @throws Exception
     */
    void sendFreemarker(Email mail) throws Exception;

    /**
     * 模版发送 thymeleaf(弃用、需要配合模板)
     * @param mail
     * @throws Exception
     */
    void sendThymeleaf(Email mail) throws Exception;

    /**
     * 队列
     * @param mail
     * @throws Exception
     */
    void sendQueue(Email mail) throws Exception;

    /**
     * Redis 队列
     * @param mail
     * @throws Exception
     */
    void sendRedisQueue(Email mail) throws Exception;

    /**
     * 邮件列表
     * @param mail
     * @return
     */
    Result listMail(Email mail);
}
