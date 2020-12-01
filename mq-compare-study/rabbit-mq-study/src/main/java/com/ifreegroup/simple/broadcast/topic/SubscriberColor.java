package com.ifreegroup.simple.broadcast.topic;

import com.rabbitmq.client.*;

/**
 * Title: SubscriberColor
 * Description: 通过通配符进行过滤，通过"."进行分隔匹配每个词，
 * 其中"#"代表0个或多个词，"*"代表一个词，
 * 只包含"#"效果等同于fanout，只包含"*"
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/30
 */
public class SubscriberColor {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "localhost";
    private static final int RABBITMQ_PORT = 5672;
    private static final String EXCHANGE_NAME = "topicExchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOSTNAME);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();
        //设置route key为通配符
        channel.queueBind(queueName, EXCHANGE_NAME, "*.color.*");
        System.out.println(" [*] Waiting for color messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
