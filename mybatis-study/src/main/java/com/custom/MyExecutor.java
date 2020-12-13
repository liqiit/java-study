package com.custom;

public interface MyExecutor {
    <T> T query(String statement, String parameter);
}
