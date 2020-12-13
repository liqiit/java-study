package com.custom;

public class MySqlSession {
    private MyConfiguration configuration;
    private MyExecutor executor;

    public MySqlSession(MyConfiguration configuration, MyExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }

    public Object selectOne(String statement, String parameter) {
        return executor.query(statement, parameter);
    }
}
