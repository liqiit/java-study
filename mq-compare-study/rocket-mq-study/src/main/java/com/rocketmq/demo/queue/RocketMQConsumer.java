package com.rocketmq.demo.queue;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Title: RocketMQConsumer
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/24
 */
public class RocketMQConsumer {
    public static void main(String[] args) {
        try {
            // 实例化消费者
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-consumer-group");

            // 设置NameServer的地址
            consumer.setNamesrvAddr("localhost:9876");

            // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
            consumer.subscribe("MyTopic2", "TagB");
            // 注册回调实现类来处理从broker拉取回来的消息
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            // 启动消费者实例
            consumer.start();
            System.out.printf("Consumer Started.%n");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
