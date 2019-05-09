package com.graduation.design.smartcampususerservice.dao;

import com.graduation.design.smartcampususerservice.entity.User;
import com.graduation.design.smartcampususerservice.entity.UserCampus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCampusDao {
    /**
     * 新增维护表条目
     * @return
     */
    int addUserCampus(UserCampus userCampus);

    /**
     * 根据user_id查询
     * @param user
     * @return
     */
    UserCampus findUserCampusByUserId(User user);

    /**
     * 更新中间表
     * @param userCampus
     * @return
     */
    int updateUserCampus(UserCampus userCampus);

    /**
     * 设置学院
     * @param userCampus
     * @return
     */
    int setAcademy(UserCampus userCampus);
    /**
     * 设置专业
     * @param userCampus
     * @return
     */
    int setMajor(UserCampus userCampus);
    /**
     * 设置学历
     * @param userCampus
     * @return
     */
    int setDegree(UserCampus userCampus);
    /**
     * 设置入学时间
     * @param userCampus
     * @return
     */
    int setAttendCampusTime(UserCampus userCampus);

}
