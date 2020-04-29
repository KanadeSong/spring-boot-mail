package com.ljj.mail.common.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 * Email封装类
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:35
 */
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 接收方邮件
     */
    @NotNull(message = "邮件收信人不能为空")
    private String[] email;
    /**
     * 主题
     */
    @NotNull(message = "邮件主题不能为空")
    private String subject;
    /**
     * 邮件内容
     */
    @NotNull(message = "邮件内容不能为空")
    private String content;

    //选填
    /**
     * 模板
     */
    private String template;
    /**
     * 自定义参数
     */
    private HashMap<String, String> kvMap;


    public Email() {
        super();
    }

    public Email(String[] email, String subject, String content, String template,
                 HashMap<String, String> kvMap) {
        super();
        this.email = email;
        this.subject = subject;
        this.content = content;
        this.template = template;
        this.kvMap = kvMap;
    }


    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public HashMap<String, String> getKvMap() {
        return kvMap;
    }

    public void setKvMap(HashMap<String, String> kvMap) {
        this.kvMap = kvMap;
    }

}
