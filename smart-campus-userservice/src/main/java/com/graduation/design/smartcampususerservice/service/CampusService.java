package com.graduation.design.smartcampususerservice.service;

import com.graduation.design.smartcampususerservice.entity.Campus;

import java.util.List;

public interface CampusService {
    /**
     * 新增大学
     *
     * @param campus
     * @return
     */
    int addCampus(Campus campus);

    /**
     * 删除大学（慎用，删除大学后，属于该大学的学生依旧存在，此时查询可能会出现异常，后续再考虑这种情况）
     *
     * @param id
     * @return
     */
    int delCampusById(int id);

    /**
     * 根据id查找大学
     *
     * @param id
     * @return
     */
    Campus findCampusById(int id);

    /**
     * 根据校名新增学校
     * @return
     */
    Campus findCampusByCampusName(String campusName);
    /**
     * 编辑大学信息（大学名、大学代码、大学省份）
     *
     * @param campus
     * @return
     */
    int editCampusDetail(Campus campus);

    /**
     * 编辑大学教务系统ip地址
     *
     * @param id
     * @param educationSystemAddress
     * @return
     */
    int editCampusEducationSystemAddress(int id, String educationSystemAddress);

    /**
     * 编辑大学所用教务系统id
     *
     * @param id
     * @param educationSystemId
     * @return
     */
    int editCampusEducationSystemId(int id,int educationSystemId);
    /**
     * 获取所有大学
     * @return
     */
    List<Campus> getAllCampus();
}
