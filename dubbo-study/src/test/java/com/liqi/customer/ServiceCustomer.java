package com.liqi.customer;

import com.liqi.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * Title: ServiceCustomer
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/3
 */
public class ServiceCustomer {
    public static void main(String[] args) {
        ReferenceConfig<HelloService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("sayhello-customer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(HelloService.class);
        HelloService helloService = referenceConfig.get();
        String message = helloService.sayHello("liqi");
        System.out.println(message);
    }
}
