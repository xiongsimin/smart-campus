package com.graduation.design.smartcampususerservice.serviceImp;

import com.graduation.design.smartcampususerservice.dao.UserDao;
import com.graduation.design.smartcampususerservice.entity.User;
import com.graduation.design.smartcampususerservice.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImp implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public int newUser(User user) {
        return this.userDao.newUser(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userDao.findUserByEmail(email);
    }

    @Override
    public int renewCheckCode(User user) {
        return this.userDao.renewCheckCode(user);
    }

    @Override
    public int setPassword(User user) {
        return this.userDao.setPassword(user);
    }

//    @Override
//    public int clearCheckCode(String email) {
//        return this.userDao.clearCheckCode(email);
//    }
}
