package com.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * @author liqi
 * @description: 测试插件
 * @date 2022/5/3下午5:53
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class ExamplePlugin implements Interceptor {
    private Properties properties = new Properties();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object returnObject = invocation.proceed();
        return returnObject;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
