package com.mybatis.mapper;

import com.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where name=#{name}")
    User queryByName(String name);


}
