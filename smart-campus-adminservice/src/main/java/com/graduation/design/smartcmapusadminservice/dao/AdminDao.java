package com.graduation.design.smartcmapusadminservice.dao;

import com.graduation.design.smartcmapusadminservice.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
    /**
     * 设置管理员密码
     *
     * @return
     */
    int setPassword(Admin admin);

    /**
     * 根据账号查找管理员
     *
     * @param account
     * @return
     */
    Admin findAdminByAccount(String account);
}
