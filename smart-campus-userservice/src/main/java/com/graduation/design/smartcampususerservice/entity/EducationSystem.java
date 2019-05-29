package com.graduation.design.smartcampususerservice.entity;

import java.io.Serializable;

public class EducationSystem implements Serializable {
    private int id;
    private String educationSystemName;
    private String microserviceName;

    public EducationSystem() {
        super();
    }

    public EducationSystem(int id, String educationSystemName, String microserviceName) {
        this.id = id;
        this.educationSystemName = educationSystemName;
        this.microserviceName = microserviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEducationSystemName() {
        return educationSystemName;
    }

    public void setEducationSystemName(String educationSystemName) {
        this.educationSystemName = educationSystemName;
    }

    public String getMicroserviceName() {
        return microserviceName;
    }

    public void setMicroserviceName(String microserviceName) {
        this.microserviceName = microserviceName;
    }

    @Override
    public String toString() {
        return "EducationSystem{" +
                "id=" + id +
                ", educationSystemName='" + educationSystemName + '\'' +
                ", microserviceName='" + microserviceName + '\'' +
                '}';
    }
}
