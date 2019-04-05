package com.graduation.design.smartcampususerservice.service;

import com.graduation.design.smartcampususerservice.entity.User;

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
}
