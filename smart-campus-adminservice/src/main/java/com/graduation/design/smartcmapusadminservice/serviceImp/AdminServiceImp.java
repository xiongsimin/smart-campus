package com.graduation.design.smartcmapusadminservice.serviceImp;

import com.graduation.design.smartcmapusadminservice.dao.AdminDao;
import com.graduation.design.smartcmapusadminservice.entity.Admin;
import com.graduation.design.smartcmapusadminservice.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("adminService")
public class AdminServiceImp implements AdminService {
    @Resource
    private AdminDao adminDao;

    @Override
    public int setPassword(Admin admin) {
        return adminDao.setPassword(admin);
    }

    @Override
    public Admin findAdminByAccount(String account) {
        return adminDao.findAdminByAccount(account);
    }
}
