package com.wdjr.springbootquick.mapper;

import com.wdjr.springbootquick.entity.User;

import java.util.List;


public interface UserMapper {

    //查询所有
    public List<User> getAll();


    public  User selectByUserName(String name);

}
