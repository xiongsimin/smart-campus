package com.graduation.design.smartcampususerservice.dao;

import com.graduation.design.smartcampususerservice.entity.Campus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CampusDao {
    /**
     * 新增大学
     * @param campus
     * @return
     */
    int addCampus(Campus campus);
}
