package com.graduation.design.smartcampususerservice.entity;

import java.io.Serializable;

public class Academy implements Serializable {
    private int id;
    private String academyName;
    private int campusId;

    public Academy() {
        super();
    }

    public Academy(int id, String academyName, int campusId) {
        this.id = id;
        this.academyName = academyName;
        this.campusId = campusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    @Override
    public String toString() {
        return "Academy{" +
                "id=" + id +
                ", academyName='" + academyName + '\'' +
                ", campusId=" + campusId +
                '}';
    }
}
