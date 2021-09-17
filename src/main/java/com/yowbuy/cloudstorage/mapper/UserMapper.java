package com.yowbuy.cloudstorage.mapper;

import com.yowbuy.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
/*    userid INT PRIMARY KEY auto_increment,
    username VARCHAR(20),
    salt VARCHAR(100),
    password VARCHAR(100),
    firstname VARCHAR(20),
    lastname VARCHAR(20)*/
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
    User getUser(String username);
    @Insert("INSERT INTO USERS(username, salt, password, firstname, lastname) " +
            "VALUES (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int createUser(User user);
}
