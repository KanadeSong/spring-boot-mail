package com.ljj.mail.service;

import com.ljj.mail.common.model.Email;
import com.ljj.mail.common.model.Result;

import javax.mail.MessagingException;

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
     *
     * @param mail 邮件对象包装
     * @throws Exception
     */
    void sendSimpleMail(Email mail) throws MessagingException;

    /**
     * 富文本邮件
     *
     * @param mail 邮件对象包装
     * @throws MessagingException
     */
    void sendHtmlMail(Email mail) throws MessagingException;


    /**
     * 发送带附件邮件
     * @param email 邮件对象包装
     * @throws MessagingException
     */
    void sendAttachmentMail(Email mail) throws MessagingException;

    /**
     * 发送带静态资源邮件
     * @param email 邮件对象包装
     * @throws MessagingException
     */
    void sendResourceMail(Email mail) throws MessagingException;

    /**
     * 队列
     *
     * @param mail
     * @throws Exception
     */
    void sendQueue(Email mail) throws MessagingException, InterruptedException;

    /**
     * Redis 队列
     *
     * @param mail
     * @throws Exception
     */
    void sendRedisQueue(Email mail) throws MessagingException;

    /**
     * 邮件列表
     *
     * @param mail
     * @return
     */
    Result listMail(Email mail);
}
