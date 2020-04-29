package com.ljj.mail.repository;

import com.ljj.mail.entity.OaEmail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * MailRepository
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:31
 */
public interface MailRepository extends JpaRepository<OaEmail, Integer> {
}
