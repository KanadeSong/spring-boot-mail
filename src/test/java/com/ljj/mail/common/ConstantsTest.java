package com.ljj.mail.common;

import com.ljj.mail.MailApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * <p>
 * ConstantsTest
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 2:19
 */
@Slf4j
public class ConstantsTest extends MailApplicationTests {

    /**
     * 分割符
     */
    @Test
    public void test() {
        log.info("SF_FILE_SEPARATOR: {}", Constants.SF_FILE_SEPARATOR);
        log.info("SF_LINE_SEPARATOR: {}", Constants.SF_LINE_SEPARATOR);
        log.info("SF_PATH_SEPARATOR: {}", Constants.SF_PATH_SEPARATOR);
    }
}
