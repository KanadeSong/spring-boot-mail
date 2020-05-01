package com.ljj.mail.dubbo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

/**
 * <p>
 * Email封装类
 * </p>
 *
 * @author LeeJack
 * @JsonIgnoreProperties(ignoreUnknown = true) jackson 2.2.2 由于vo中缺少json的某个字段属性引起 UnrecognizedPropertyException
 * @date Created in 2020/4/29 0:35
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
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
     * 抄送人
     */
    private String[] cc;

    /**
     * 附件位置
     */
    private String[] attachment;

    /**
     * 自定义参数
     */
    private HashMap<String, String> kvMap;

    @Override
    public String toString() {
        return "Email{" +
                "email=" + Arrays.toString(email) +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", cc=" + Arrays.toString(cc) +
                ", attachment=" + Arrays.toString(attachment) +
                ", kvMap=" + kvMap +
                '}';
    }
}
