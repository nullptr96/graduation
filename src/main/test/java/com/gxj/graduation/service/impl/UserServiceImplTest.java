package com.gxj.graduation.service.impl;

import org.junit.Test;

public class UserServiceImplTest {
    @Test
    public void testQueryById(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.queryById("admin");

    }

}