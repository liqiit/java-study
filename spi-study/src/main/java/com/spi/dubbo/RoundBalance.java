package com.spi.dubbo;

/**
 * @ClassName RoundBalance
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/4/15 11:23
 * @Version 1.0
 **/
public class RoundBalance implements Balance {
    @Override
    public void doRule(String hello) {
        System.out.println("轮询负载均衡 say " +hello);
    }
}
