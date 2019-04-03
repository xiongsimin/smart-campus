package com.graduation.design.smartcampususerservice.serviceImp;

import com.graduation.design.smartcampususerservice.dao.CampusDao;
import com.graduation.design.smartcampususerservice.entity.Campus;
import com.graduation.design.smartcampususerservice.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("campusService")
public class CampusServiceImp implements CampusService {
    @Resource
    private CampusDao campusDao;

    @Override
    public int addCampus(Campus campus) {
        return this.campusDao.addCampus(campus);
    }
}
