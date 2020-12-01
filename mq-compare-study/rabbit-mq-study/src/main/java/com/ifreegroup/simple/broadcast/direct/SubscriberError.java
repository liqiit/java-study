package com.ifreegroup.simple.broadcast.direct;

import com.rabbitmq.client.*;

/**
 * Title: SubscriberError
 * Description: 消费者通过bindkey进行绑定，
 * 只关心routekey与 bindkey 完全匹配的消息，
 * 当前消费者只关心bindkey为error的消息
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/25
 */
public class SubscriberError {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "localhost";
    private static final int RABBITMQ_PORT = 5672;
    private static final String EXCHANGE_NAME = "directExchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOSTNAME);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();
        //只会消费bindkey为error的消息
        channel.queueBind(queueName, EXCHANGE_NAME, "error");

        System.out.println(" [*] Waiting for error messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            //确认收到消息，只确认当前消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        //手动确认
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> {
        });
    }
}
