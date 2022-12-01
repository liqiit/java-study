package com.liqi.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Indexed;

import java.util.concurrent.TimeUnit;

/**
 * Title: NacosApplication
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/28
 */
@Indexed
@SpringBootApplication
public class NacosApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosApplication.class, args);
//        while(true) {
//            String userName = applicationContext.getEnvironment().getProperty("spring.datasource.type");
//            String userAge = applicationContext.getEnvironment().getProperty("spring.datasource.driver-class-name");
//            //获取当前部署的环境
//            String currentEnv = applicationContext.getEnvironment().getProperty("current.env");
//            System.err.println("in "+currentEnv+" enviroment; "+"user name :" + userName + "; age: " + userAge);
//            TimeUnit.SECONDS.sleep(1);
//        }
    }
}
