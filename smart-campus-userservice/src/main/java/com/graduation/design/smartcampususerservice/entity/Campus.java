package com.graduation.design.smartcampususerservice.entity;

import java.io.Serializable;

public class Campus implements Serializable {
    private int id;
    private String campusName;
    private String campusCode;
    private String province;
    private int officialSystemId;

    public Campus() {
        super();
    }

    public Campus(int id, String campusName, String campusCode, String province, int officialSystemId) {
        this.id = id;
        this.campusName = campusName;
        this.campusCode = campusCode;
        this.province = province;
        this.officialSystemId = officialSystemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getOfficialSystemId() {
        return officialSystemId;
    }

    public void setOfficialSystemId(int officialSystemId) {
        this.officialSystemId = officialSystemId;
    }

    @Override
    public String toString() {
        return "campus{" +
                "id=" + id +
                ", campusName='" + campusName + '\'' +
                ", campusCode='" + campusCode + '\'' +
                ", province='" + province + '\'' +
                ", officialSystemId=" + officialSystemId +
                '}';
    }
}
