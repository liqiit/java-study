package com.spi.dubbo;

import com.spi.jdk.Loader;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @ClassName JunitTest
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/4/15 11:29
 * @Version 1.0
 **/
public class JunitTest {
    @Test
    public void testBalance() {
        String hello="hello";
        ExtensionLoader<Balance> extensionLoader = ExtensionLoader.getExtensionLoader(Balance.class);
        Balance balance = extensionLoader.getExtension("random");
        balance.doRule(hello);
        System.out.println("hello");
        Balance roundBalance = extensionLoader.getExtension("round");
        roundBalance.doRule(hello);

    }

}
