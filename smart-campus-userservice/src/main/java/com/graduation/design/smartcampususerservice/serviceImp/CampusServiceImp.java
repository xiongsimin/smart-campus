package com.graduation.design.smartcampususerservice.serviceImp;

import com.graduation.design.smartcampususerservice.dao.CampusDao;
import com.graduation.design.smartcampususerservice.entity.Campus;
import com.graduation.design.smartcampususerservice.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("campusService")
public class CampusServiceImp implements CampusService {
    @Resource
    private CampusDao campusDao;

    @Override
    public int addCampus(Campus campus) {
        return this.campusDao.addCampus(campus);
    }

    @Override
    public int delCampusById(int id) {
        return this.campusDao.delCampusById(id);
    }

    @Override
    public Campus findCampusById(int id) {
        return this.campusDao.findCampusById(id);
    }

    @Override
    public Campus findCampusByCampusName(String campusName) {
        return campusDao.findCampusByCampusName(campusName);
    }

    @Override
    public int editCampusDetail(Campus campus) {
        return this.campusDao.editCampusDetail(campus);
    }

    @Override
    public int editCampusEducationSystemAddress(int id, String educationSystemAddress) {
        return this.campusDao.editCampusEducationSystemAddress(id,educationSystemAddress);
    }

    @Override
    public int editCampusEducationSystemId(int id, int educationSystemId) {
        return this.campusDao.editCampusEducationSystemId(id,educationSystemId);
    }

    @Override
    public List<Campus> getAllCampus() {
        return campusDao.getAllCampus();
    }
}
