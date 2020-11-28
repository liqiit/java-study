package com.rabbitmq.demo.queue;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Title: RabbitMqProducer
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/23
 */
public class RabbitMqProducer {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "localhost";
    private static final int RABBITMQ_PORT = 5672;
    private static final String RABBITMQ_DEFAULT_QUEUE = "demo-queue";

    public static void main(String[] args) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOSTNAME);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        Channel channel ;
        try (Connection connection = connectionFactory.newConnection()) {
            channel = connection.createChannel();
            //对queue设置ttl
            Map<String, Object> params = new HashMap<>();
            params.put("x-message-ttl", 60000);
            channel.queueDeclare(RABBITMQ_DEFAULT_QUEUE, false, false, false, null);
            String message = "hello world";
            //对某个消息设置ttl(time to live)
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .expiration("60000")
                    .build();
            channel.basicPublish("", RABBITMQ_DEFAULT_QUEUE, properties, message.getBytes());
            System.out.println("sending message [" + message + "]");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
