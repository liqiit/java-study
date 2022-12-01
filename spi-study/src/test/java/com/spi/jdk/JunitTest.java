package com.spi.jdk;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @ClassName JunitTest
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/4/15 10:15
 * @Version 1.0
 **/
public class JunitTest {
    @Test
    public void testFileLoader(){
        ServiceLoader<Loader> loaders = ServiceLoader.load(Loader.class);
        loaders.forEach(Loader::load);
    }
}
