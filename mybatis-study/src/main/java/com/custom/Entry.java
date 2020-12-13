package com.custom;

public class Entry {
    public static void main(String[] args) {
        MySqlSession sqlSession = new MySqlSession(new MyConfiguration(), new MySimpleExecutor());
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.queryById(1L);
    }
}
