package com.junehua.blog.service.impl;

import com.junehua.blog.dao.UserDao;
import com.junehua.blog.pojo.User;
import com.junehua.blog.service.IUserService;
import com.junehua.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public User checkUser(String username,String password) {
        return userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
