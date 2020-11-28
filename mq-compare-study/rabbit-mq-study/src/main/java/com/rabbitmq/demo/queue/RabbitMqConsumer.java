package com.rabbitmq.demo.queue;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Title: RabbitMqConsumer
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/23
 */
public class RabbitMqConsumer {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "localhost";
    private static final int RABBITMQ_PORT = 5672;
    private static final String RABBITMQ_DEFAULT_QUEUE = "demo-queue";

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOSTNAME);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RABBITMQ_DEFAULT_QUEUE, false, false, false, null);
        System.out.println("waiting.....");
        DeliverCallback deliverCallback= (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" Received '" + message + "'");
        };
        channel.basicConsume(RABBITMQ_DEFAULT_QUEUE,true,deliverCallback,consumerTag -> {});


    }
}
