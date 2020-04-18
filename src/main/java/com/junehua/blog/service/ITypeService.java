package com.junehua.blog.service;

import com.junehua.blog.pojo.Type;

import java.util.List;

public interface ITypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAdminType();

    Type getTypeByName(String name);

    int updateType(Type type);

    int deleteType(Long id);
}
