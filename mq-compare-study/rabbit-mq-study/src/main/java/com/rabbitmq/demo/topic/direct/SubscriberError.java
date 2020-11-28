package com.rabbitmq.demo.topic.direct;

import com.rabbitmq.client.*;

/**
 * Title: SubscriberError
 * Description: 消费者通过bind key进行绑定，只关心routekey 与 bind key 完全匹配的消息
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/25
 */
public class SubscriberError {
    private static final String EXCHANGE_NAME = "directLogs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "error");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
