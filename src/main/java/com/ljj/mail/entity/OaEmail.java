package com.ljj.mail.entity;

import com.ljj.mail.dubbo.model.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * <p>
 * OaEmail oa_email表映射
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:30
 */
@Entity
@Table(name = "oa_email")
public class OaEmail implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 接收人邮箱(多个逗号分开)
     */
    private String receiveEmail;

    /**
     * 抄送人邮箱(多个逗号分开)
     */
    private String ccEmail;

    /**
     * 主题
     */
    private String subject;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 附件地址
     */
    private String attachment;

    /**
     * 发送时间
     */
    private Timestamp sendTime;

    public OaEmail() {
    }

    public OaEmail(Email mail) {
        this.receiveEmail = Arrays.toString(mail.getEmail());
        this.ccEmail = Arrays.toString(mail.getCc());
        this.subject = mail.getSubject();
        this.content = mail.getContent();
        this.attachment = Arrays.toString(mail.getAttachment());
        this.sendTime = new Timestamp(System.currentTimeMillis());
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "TestSequence", sequenceName = "SEQ_Test", allocationSize=1)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    @Column(name = "receive_email", nullable = false, length = 500)
    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setCcEmail(String ccEmail) {
        this.ccEmail = ccEmail;
    }

    @Column(name = "cc_email", length = 500)
    public String getCcEmail() {
        return ccEmail;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "subject", nullable = false, length = 100)
    public String getSubject() {
        return subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "content", columnDefinition = "TEXT", nullable = false, length = 65535)
    public String getContent() {
        return content;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Column(name = "attachment", columnDefinition = "TEXT", length = 65535)
    public String getAttachment() {
        return attachment;
    }


    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Column(name = "send_time", nullable = false, length = 19)
    public Timestamp getSendTime() {
        return sendTime;
    }

    @Override
    public String toString() {
        return "OaEmail{" +
                "id=" + id +
                ", receiveEmail='" + receiveEmail + '\'' +
                ", ccEmail='" + ccEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", attachment='" + attachment + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
