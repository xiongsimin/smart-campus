package com.graduation.design.smartcampususerservice.service;

import com.graduation.design.smartcampususerservice.entity.User;
import com.graduation.design.smartcampususerservice.entity.UserCampus;

public interface UserService {
    /**
     * 新增新用户（刚提交用户申请，尚未通过验证码验证）
     *
     * @param user
     * @return
     */
    int newUser(User user);

    //    /**
//     * 清除失效验证码
//     * @param email
//     * @return
//     */
//    int clearCheckCode(String email);

    /**
     * 根据email查找用户
     *
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 更新验证码
     *
     * @param user
     * @return
     */
    int renewCheckCode(User user);

    /**
     * 设置密码
     *
     * @param user
     * @return
     */
    int setPassword(User user);

    /**
     * 设置个人信息（昵称、性别、学校、教务系统学号、教务系统登录密码）
     * @param user
     * @param userCampus
     * @return 如果正常完成操作，返回值应该为2
     */
    int setPersonalDetail(User user, UserCampus userCampus);

    /**
     * 更新昵称
     * @param user
     * @return
     */
    int setNickname(User user);

    /**
     * 更新性别
     * @param user
     * @return
     */
    int setSex(User user);
}
