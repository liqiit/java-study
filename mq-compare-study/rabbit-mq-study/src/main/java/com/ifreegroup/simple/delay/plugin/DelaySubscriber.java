package com.ifreegroup.simple.delay.plugin;

import com.rabbitmq.client.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: DelaySubscriber
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/1
 */
public class DelaySubscriber {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "8.210.252.134";
    private static final int RABBITMQ_PORT = 5672;
    private static final String DELAY_EXCHANGE_NAME="delay_exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOSTNAME);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = channel.queueDeclare().getQueue();

        //只会消费bindkey为delay的消息
        channel.queueBind(queueName, DELAY_EXCHANGE_NAME, "delay");

        System.out.println(" [*] Waiting for warn messages. To exit press CTRL+C");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            Date now = new Date();
            System.out.println(" [x] Received '" + message + "'");
            System.out.println(" [x] CurrentTime："+sf.format(now));
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
