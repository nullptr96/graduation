package com.gxj.graduation.dao;

import com.gxj.graduation.entity.User;

public interface UserDao {
    int addUser(User user);
    User queryById(String id);

}
