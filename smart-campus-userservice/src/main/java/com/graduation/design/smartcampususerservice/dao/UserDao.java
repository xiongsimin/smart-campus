package com.graduation.design.smartcampususerservice.dao;

import com.graduation.design.smartcampususerservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
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
//    int clearCheckCode(@Param("email") String email);

    /**
     * 根据email查找用户
     * @param email
     * @return
     */
    User findUserByEmail(@Param("email") String email);

    /**
     * 更新验证码
     * @param user
     * @return
     */
    int renewCheckCode(User user);

    /**
     * 设置密码
     * @param user
     * @return
     */
    int setPassword(User user);

    /**
     * 设置个人信息（昵称、性别）
     * @return
     */
    int setPersonalDetail(User user);

    /**
     * 设置昵称
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
