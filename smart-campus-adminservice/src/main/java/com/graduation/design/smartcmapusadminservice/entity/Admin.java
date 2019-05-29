package com.graduation.design.smartcmapusadminservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Admin implements Serializable {
    private int id;
    private String account;
    private String name;
    private Timestamp lastLoginTime;
    private String password;

    public Admin() {
        super();
    }

    public Admin(int id, String account, String name, Timestamp lastLoginTime, String password) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.lastLoginTime = lastLoginTime;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", password='" + password + '\'' +
                '}';
    }
}
