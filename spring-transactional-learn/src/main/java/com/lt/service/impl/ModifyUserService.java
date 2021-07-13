package com.lt.service.impl;

import com.lt.dao.UserDao;
import com.lt.model.User;
import com.lt.service.UserModifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liangtao
 * @description
 * @Date 2021/7/12
 **/
@Service
public class ModifyUserService implements UserModifyService {
    @Resource
    UserDao userDao;

    @Override
    public User getById(int id) {
        return userDao.list().stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @Override
    public void delByName(String name) {
        userDao.deleteByName(name);
    }

    @Override
    public void updateById(User user) {
        userDao.updateById(user);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }
}
