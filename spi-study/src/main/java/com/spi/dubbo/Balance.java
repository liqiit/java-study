package com.spi.dubbo;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Balance {
    void doRule(String hello);
}
