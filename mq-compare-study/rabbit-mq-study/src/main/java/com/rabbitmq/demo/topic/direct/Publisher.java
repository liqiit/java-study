package com.rabbitmq.demo.topic.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Title: Publisher
 * Description: direct类型exchange可以对消息进行分类，通过routekey进行分类
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/25
 */
public class Publisher {
    private static final String EXCHANGE_NAME = "directLogs";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT);
            String message = "error info";
            channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
