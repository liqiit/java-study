package com.rocketmq.demo.queue;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Title: RocketMQProducer
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/24
 */
public class RocketMQProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("my-producer-group");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();
            Message msg = new Message("MyTopic2",
                    "TagB",
                    ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            SendResult result = producer.send(msg);
            System.out.printf("%s%n", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
