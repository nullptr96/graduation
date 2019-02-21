package com.gxj.graduation.service.impl;

import baseUnitTest.BaseUnitTest;
import com.gxj.graduation.entity.User;
import com.gxj.graduation.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class UserServiceImplTest extends BaseUnitTest {
    @Autowired
    UserService userService;
    @Test
    public void testQueryById(){
        User user = userService.queryById("admin");
        Assert.assertEquals(user.getId(),"admin");
        Assert.assertEquals(user.getPassword(),"123456");


    }

}