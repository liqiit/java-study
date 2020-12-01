package com.ifreegroup.simple.delay.plugin;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: DelayPublisher
 * Description: 通过插件方式实现延迟消息，需要下载安装并启用
 * 下载地址：https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases
 * 启用命令：rabbitmq-plugins enable rabbitmq_delayed_message_exchange
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/1
 */
public class DelayPublisher {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "8.210.252.134";
    // 队列名称
    private final static String EXCHANGE_NAME = "delay_exchange";
    private final static String ROUTING_KEY = "delay";

    public static void main(String[] argv) throws Exception {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOSTNAME);
        factory.setUsername(RABBITMQ_USERNAME);
        factory.setPassword(RABBITMQ_PASSWORD);
        factory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        // 声明x-delayed-type类型的exchange
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true, false, args);

        Date now = new Date();
        String readyToPushContent = "publish at " + sf.format(now);
        //头信息指定延时时间，单位：毫秒
        Map<String, Object> headers = new HashMap<>();
        headers.put("x-delay", 6000);

        AMQP.BasicProperties.Builder props = new AMQP.BasicProperties.Builder()
                .headers(headers);
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, props.build(),
                readyToPushContent.getBytes());

        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
