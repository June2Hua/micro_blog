package com.junehua.blog.service;

import com.junehua.blog.pojo.User;

public interface IUserService {

    User checkUser(String username,String password);
}
