package com.graduation.design.smartcampususerservice.dao;

import com.graduation.design.smartcampususerservice.entity.Academy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AcademyDao {
    int addAcademy(Academy academy);

    int delAcademyById(@Param("id") int id);

    int editAcademy(Academy academy);

    Academy findAcademyById(@Param("id") int id);

    List<Academy> findAcademyByCampusId(@Param("campusId") int campusId);
}
