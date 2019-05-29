package com.graduation.design.smartcampususerservice.serviceImp;

import com.graduation.design.smartcampususerservice.dao.CampusDao;
import com.graduation.design.smartcampususerservice.dao.UserCampusDao;
import com.graduation.design.smartcampususerservice.dao.UserDao;
import com.graduation.design.smartcampususerservice.entity.Campus;
import com.graduation.design.smartcampususerservice.entity.User;
import com.graduation.design.smartcampususerservice.entity.UserCampus;
import com.graduation.design.smartcampususerservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImp implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UserCampusDao userCampusDao;
    @Resource
    private CampusDao campusDao;
    @Override
    public int newUser(User user) {
        return this.userDao.newUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User findUserByEmail(String email) {
        User user=userDao.findUserByEmail(email);
        if(user!=null){
            UserCampus userCampus=userCampusDao.findUserCampusByUserId(user);
            if(userCampus!=null){
                Campus campus=campusDao.findCampusById(userCampus.getCampusId());
                user.setCampus(campus);
                user.setUserCampus(userCampus);
            }
        }
        return user;
    }

    @Override
    public int renewCheckCode(User user) {
        return this.userDao.renewCheckCode(user);
    }

    @Override
    public int setPassword(User user) {
        return this.userDao.setPassword(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setPersonalDetail(User user, UserCampus userCampus) {
        //如果已实名，则只更新用户部分信息（昵称）
        if(user.getRealNameOrNot()==1){
            userDao.setNickname(user);
        }else {//未实名允许更改学校、学院、性别等信息
            if(userCampus!=null){//请求中学院等信息不为空
                if(userCampusDao.findUserCampusByUserId(user)!=null){//已经存在对应中间表记录,更新记录
                    userCampusDao.updateUserCampus(userCampus);
                }else {//不存在对应中间表记录,新增记录

                }
            }else{//请求中学院等信息为空
                userDao.setPersonalDetail(user);
            }
        }
        int i=0;
        if(this.userCampusDao.addUserCampus(userCampus)==1){
            i=i+1;
        }else {
            try {
                throw new Exception("添加user_campus表记录时出错！");
            } catch (Exception e) {
                e.printStackTrace();
                i=0;
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return i;
            }
        }
        user.setUserCampusId(userCampus.getId());
        if(this.userDao.setPersonalDetail(user)==1){
            i=i+1;
        }else {
            try {
                throw new Exception("设置user表时出错！");
            } catch (Exception e) {
                e.printStackTrace();
                i=0;
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return i;
            }
        }
        return i;
    }

    @Override
    public int setNickname(User user) {
        return userDao.setNickname(user);
    }

    @Override
    public int setSex(User user) {
        return userDao.setSex(user);
    }

    @Override
    public int setUserImagePath(User user) {
        return userDao.setUserImagePath(user);
    }

//    @Override
//    public int clearCheckCode(String email) {
//        return this.userDao.clearCheckCode(email);
//    }
}
