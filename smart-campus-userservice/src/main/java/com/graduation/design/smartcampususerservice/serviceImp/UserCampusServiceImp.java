package com.graduation.design.smartcampususerservice.serviceImp;

import com.graduation.design.smartcampususerservice.dao.UserCampusDao;
import com.graduation.design.smartcampususerservice.entity.User;
import com.graduation.design.smartcampususerservice.entity.UserCampus;
import com.graduation.design.smartcampususerservice.service.UserCampusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userCampusService")
public class UserCampusServiceImp implements UserCampusService {
    @Resource
    private UserCampusDao userCampusDao;

    @Override
    public int addUserCampus(UserCampus userCampus) {
        return userCampusDao.addUserCampus(userCampus);
    }

    @Override
    public UserCampus findUserCampusByUserId(User user) {
        return userCampusDao.findUserCampusByUserId(user);
    }

    @Override
    public int updateUserCampus(UserCampus userCampus) {
        return userCampusDao.updateUserCampus(userCampus);
    }

    @Override
    public int setAcademy(UserCampus userCampus) {
        return userCampusDao.setAcademy(userCampus);
    }

    @Override
    public int setMajor(UserCampus userCampus) {
        return userCampusDao.setMajor(userCampus);
    }

    @Override
    public int setDegree(UserCampus userCampus) {
        return userCampusDao.setDegree(userCampus);
    }

    @Override
    public int setAttendCampusTime(UserCampus userCampus) {
        return userCampusDao.setAttendCampusTime(userCampus);
    }

}
