package com.ifreegroup.reliability.connection;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Title: AutoRecoverConnection
 * Description:连接自动恢复启用配置，不能保证100%重连成功
 * 触发自动恢复条件：
 * 1、连接的IO循环中抛出IO异常
 * 2、socket读操作超时
 * 3、失去服务端心跳
 * 4、连接的IO循环中抛出其他未知异常
 *
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/30
 */
public class AutoRecoverConnection {
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";
    private static final String RABBITMQ_VIRTUALHOST = "/";
    private static final String RABBITMQ_HOSTNAME = "localhost";
    private static final int RABBITMQ_PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        //用户guest默认只能连接本地服务器，如果需要连接远程服务需要
        factory.setUsername(RABBITMQ_USERNAME);
        factory.setPassword(RABBITMQ_PASSWORD);
        factory.setVirtualHost(RABBITMQ_VIRTUALHOST);
        factory.setHost(RABBITMQ_HOSTNAME);
        factory.setPort(RABBITMQ_PORT);
        //连接自动恢复配置项, 4.0.0之后版本默认启动
        factory.setAutomaticRecoveryEnabled(true);
        //如果第一次恢复连接失败，后续每隔10秒尝试恢复一次
        factory.setNetworkRecoveryInterval(10000);
        //进行自动尝试连接其他服务器
        Address[] addresses = {new Address("127.0.0.1"), new Address("127.0.0.1")};
        factory.newConnection(addresses);
        //如果自动重连不能成功，需要手动捕获异常增加重连逻辑
        Connection conn = null;
        Channel channel=null;
        try {
            conn = factory.newConnection();
            //设置连接的唯一标识，名称将被忽略
            conn.setId("autoRecoverConnection");
            channel=conn.openChannel().get();

        } catch (java.net.ConnectException e) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            //进行重连
            conn = factory.newConnection();

        }finally {
            if(channel!=null){
                //可以不显示调用，连接关闭会自动关闭通道
                channel.close();
            }
            if(conn!=null){
                conn.close();
            }

        }
        //增加连接恢复监听器
        ((Recoverable)conn).addRecoveryListener(new RecoveryListener() {
            @Override
            public void handleRecovery(Recoverable recoverable) {
                System.out.println("连接自动恢复已完成");
            }

            @Override
            public void handleRecoveryStarted(Recoverable recoverable) {
                System.out.println("连接自动恢复开始启动");
            }
        });
    }
}
