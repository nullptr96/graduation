package com.gxj.graduation.service;

import com.gxj.graduation.entity.User;

public interface UserService {
    int addUser(User user);
    User queryById(String id);
}
