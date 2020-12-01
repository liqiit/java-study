package com.ifreegroup.simple.delay.ttl;

import com.rabbitmq.client.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: DelaySubscriber
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/1
 */
public class DelaySubscriber {

    public static void main(String[] argv) throws Exception {
        // 创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("8.210.252.134");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 通过链接工厂创建链接
        Connection connection = connectionFactory.newConnection();

        // 通过链接创建通道（channel）
        Channel channel = connection.createChannel();

        // 定义正常交换机、队列等信息
        String dlxExchangeName = "dlx_exchange_name";
        String exchangeType = "direct";
        String dlxRoutingKey = "dlx_routingKey";
        String dlxQueueName = "dlx_queue";

        // 定义死信队列交换机、队列等信息
        String dlxTestExchangeName = "dlx_test_exchange_name";
        String dlxTestRoutingKey = "dlx_test_routingKey";
        String dlxTestQueueName = "dlx_test_queue";

        // 声明一个正常的交换机、队列和绑定关系
        channel.exchangeDeclare(dlxExchangeName, exchangeType, true, false, null);

        // 声明正常队列并指定死信交换机，绑定交换机和队列
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", dlxTestExchangeName);
        channel.queueDeclare(dlxQueueName, true, false, false, arguments);
        channel.queueBind(dlxQueueName, dlxExchangeName, dlxRoutingKey);


        // 死信队列的交换机、队列声明和绑定关系
        channel.exchangeDeclare(dlxTestExchangeName, "direct", true, false, null);
        channel.queueDeclare(dlxTestQueueName, true, false, false, null);
        channel.queueBind(dlxTestQueueName, dlxTestExchangeName, dlxRoutingKey);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            Date now = new Date();
            System.out.println(" [x] Received '" + message + "'");
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            System.out.println(" [x] CurrentTime："+sf.format(now));
        };
        // 6. 设置 channel
        channel.basicConsume(dlxTestQueueName, true, deliverCallback,consumerTag -> {});

        System.out.println("消费端启动成功！");
    }
}
