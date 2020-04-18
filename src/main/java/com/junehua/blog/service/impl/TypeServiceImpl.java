package com.junehua.blog.service.impl;

import com.junehua.blog.dao.TypeDao;
import com.junehua.blog.pojo.Type;
import com.junehua.blog.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Transactional 可以作用于接口、接口方法、类以及类方法上。
 * 当作用于类上时，该类的所有 public 方法将都具有该类型的事务属性，
 * 同时，我们也可以在方法级别使用该标注来覆盖类级别的定义。
 *
 * 虽然 @Transactional 注解可以作用于接口、接口方法、类以及类方法上，
 * 但是 Spring 建议不要在接口或者接口方法上使用该注解，因为这只有在使用基于接口的代理时它才会生效。
 * 另外， @Transactional 注解应该只被应用到 public 方法上，这是由 Spring AOP 的本质决定的。
 * 如果你在 protected、private 或者默认可见性的方法上使用 @Transactional 注解，这将被忽略，也不会抛出任何异常。
 *
 * 默认情况下，只有来自外部的方法调用才会被AOP代理捕获，
 * 也就是，类内部方法调用本类内部的其他方法并不会引起事务行为，
 * 即使被调用方法使用@Transactional注解进行修饰。
 */

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeDao typeDao;

    //事务注解
    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getTypeById(id);
    }

    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public List<Type> getAdminType() {
        return typeDao.getAdminType();
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
