package com.liqi.provider;

import com.liqi.service.HelloService;
import com.liqi.service.impl.HelloServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.CountDownLatch;

/**
 * Title: ServiceProvider
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/3
 */
public class ServiceProvider {
    public static void main(String[] args) {
        try {
            ServiceConfig<HelloService>serviceConfig=new ServiceConfig<>();
            serviceConfig.setApplication(new ApplicationConfig("sayhello-dubbo-service"));
            serviceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
            serviceConfig.setInterface(HelloService.class);
            serviceConfig.setRef(new HelloServiceImpl());
            serviceConfig.export();
            System.out.println("dubbo service started");
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
