package com.spi.dubbo;

/**
 * @ClassName RandomBalance
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/4/15 11:23
 * @Version 1.0
 **/
public class RandomBalance implements Balance {
    @Override
    public void doRule(String hello) {
        System.out.println("随机负载均衡 say "+hello);
    }
}
