package com.graduation.design.smartcampususerservice.serviceImp;

import com.graduation.design.smartcampususerservice.dao.AcademyDao;
import com.graduation.design.smartcampususerservice.entity.Academy;
import com.graduation.design.smartcampususerservice.service.AcademyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("academyService")
public class AcademyServiceImp implements AcademyService {
    @Resource
    private AcademyDao academyDao;

    @Override
    public int addAcademy(Academy academy) {
        return this.academyDao.addAcademy(academy);
    }

    @Override
    public int delAcademyById(int id) {
        return this.academyDao.delAcademyById(id);
    }

    @Override
    public int editAcademy(Academy academy) {
        return this.academyDao.editAcademy(academy);
    }

    @Override
    public Academy findAcademyById(int id) {
        return this.academyDao.findAcademyById(id);
    }

    @Override
    public List<Academy> findAcademyByCampusId(int campusId) {
        return this.academyDao.findAcademyByCampusId(campusId);
    }
}
