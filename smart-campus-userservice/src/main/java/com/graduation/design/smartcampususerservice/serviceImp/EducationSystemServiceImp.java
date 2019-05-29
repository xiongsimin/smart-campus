package com.graduation.design.smartcampususerservice.serviceImp;

import com.graduation.design.smartcampususerservice.dao.EducationSystemDao;
import com.graduation.design.smartcampususerservice.entity.EducationSystem;
import com.graduation.design.smartcampususerservice.service.EducationSystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("educationService")
public class EducationSystemServiceImp implements EducationSystemService {
    @Resource
    private EducationSystemDao educationSystemDao;

    @Override
    public int addEducationSystem(EducationSystem educationSystem) {
        return this.educationSystemDao.addEducationSystem(educationSystem);
    }

    @Override
    public int editEducationSystem(EducationSystem educationSystem) {
        return this.educationSystemDao.editEducationSystem(educationSystem);
    }

    @Override
    public int delEducationSystem(int id) {
        return this.educationSystemDao.delEducationSystem(id);
    }

    @Override
    public EducationSystem findEducationSystemById(int id) {
        return this.educationSystemDao.findEducationSystemById(id);
    }

    @Override
    public List<EducationSystem> getAllEducationSystem() {
        return this.educationSystemDao.getAllEducationSystem();
    }
}
