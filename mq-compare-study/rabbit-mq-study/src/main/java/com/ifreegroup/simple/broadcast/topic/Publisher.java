package com.ifreegroup.simple.broadcast.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Title: DelayPublisher
 * Description: 本示例用于演示topic类型exchange的使用方式，
 * bindkey和routekey通过通配符进行匹配
 * 更多详细参见：https://www.rabbitmq.com/tutorials/tutorial-five-java.html
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/30
 */
public class Publisher {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "localhost";
    private static final int RABBITMQ_PORT = 5672;
    private static final String EXCHANGE_NAME = "topicExchange";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOSTNAME);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        try (Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()) {
            //指定exchange类型为topic
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String message = "orange color";
            channel.basicPublish(EXCHANGE_NAME, "rabbit.color.orange", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Send message'" + message + "'");

            message="fast speed";
            channel.basicPublish(EXCHANGE_NAME, "color.orange.speed", null, message.getBytes("UTF-8"));

            System.out.println(" [x] Send message '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
