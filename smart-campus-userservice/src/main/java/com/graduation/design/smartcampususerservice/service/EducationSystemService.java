package com.graduation.design.smartcampususerservice.service;

import com.graduation.design.smartcampususerservice.entity.EducationSystem;

import java.util.List;

public interface EducationSystemService {
    /**
     * 新增教务系统
     *
     * @param educationSystem
     * @return
     */
    int addEducationSystem(EducationSystem educationSystem);

    /**
     * 编辑教务系统
     *
     * @param educationSystem
     * @return
     */
    int editEducationSystem(EducationSystem educationSystem);

    /**
     * 删除教务系统
     *
     * @param id
     * @return
     */
    int delEducationSystem(int id);

    /**
     * 根据id查找教务系统
     *
     * @param id
     * @return
     */
    EducationSystem findEducationSystemById(int id);

    /**
     * 获取所有教务系统
     * @return
     */
    List<EducationSystem> getAllEducationSystem();
}
