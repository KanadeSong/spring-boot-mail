package com.ljj.mail.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * RedisListener 监听redis队列
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:36
 */
@Component
@Slf4j
public class RedisListener {

    @Bean
    RedisMessageListenerContainer container(
            RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        log.info("启动监听");
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("mail"));
        return container;
    }

    /**
     * 拦截到redis队列的消息执行 Receiver的方法
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /**
     * @param latch
     * @return
     */
    @Bean
    Receiver Receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }


    /**
     * @return
     */
    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    /**
     * @param connectionFactory
     * @return
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
