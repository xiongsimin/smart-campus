package com.graduation.design.smartcampususerservice.service;

import com.graduation.design.smartcampususerservice.entity.Academy;

import java.util.List;

public interface AcademyService {
    int addAcademy(Academy academy);

    int delAcademyById(int id);

    int editAcademy(Academy academy);

    Academy findAcademyById(int id);

    List<Academy> findAcademyByCampusId(int campusId);
}
