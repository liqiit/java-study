package com.ifreegroup.simple.peer2peer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Title: Producer
 * Description:本示例演示点对点消息的发送，发布消息时采用默认exchange（默认为""）
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/30
 */
public class Producer {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "8.210.252.134";
    private static final int RABBITMQ_PORT = 5672;
    private final static String QUEUE_NAME = "p2pQueue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOSTNAME);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            //点对点消息不需要指定exchange
            String message = "hello world";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Send message'" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
