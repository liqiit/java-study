package com.liqi.service.impl;

import com.liqi.service.HelloService;

/**
 * Title: HelloServiceImpl
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/3
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
