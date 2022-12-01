package com.mybatis.demo;

import com.mybatis.entity.User;
import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Demo {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Demo.class.getResourceAsStream("/mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.queryByName("liqi");


            System.out.println(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
