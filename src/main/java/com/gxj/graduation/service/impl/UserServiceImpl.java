package com.gxj.graduation.service.impl;

import com.gxj.graduation.dao.UserDao;
import com.gxj.graduation.entity.User;
import com.gxj.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User queryById(String id) {
        return userDao.queryById(id);
    }
}