package com.ljj.mail.common.queue;

import com.ljj.mail.dubbo.model.Email;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>
 * MailQueue 邮件队列
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:35
 */
public class MailQueue {
    /**
     * 队列大小
     */
    private static final int QUEUE_MAX_SIZE = 1000;

    /**
     * 阻塞队列
     */
    private static BlockingQueue<Email> blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    /**
     * 私有构造函数，外部无法直接实例化
     */
    private MailQueue() {
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static MailQueue queue = new MailQueue();
    }


    /**
     * 单列队列
     */
    public static MailQueue getMailQueue() {
        return SingletonHolder.queue;
    }

    /**
     * 生产入队
     */
    public void produce(Email mail) throws InterruptedException {
        blockingQueue.put(mail);
    }

    /**
     * 消费出队
     */
    public Email consume() throws InterruptedException {
        return blockingQueue.take();
    }

    /**
     * 获取队列大小
     */
    public int size(){
        return blockingQueue.size();
    }
}
