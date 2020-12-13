package com.custom;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MyConfiguration {
    public <T> T getMapper(Class<T> clazz, MySqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                new MyMapperProxy(sqlSession));
    }

    static class TestMapperXml {
        public static final String namespace = "com.custom.TestMapper";
        public static Map<String, String> methodSQL = new HashMap<>();

        static {
            methodSQL.put("selectById", "select * from test where id=%d");
        }
    }
}
