package com.graduation.design.smartcampususerservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    private int id;
    private String nickname;
    private String sex;
    private String email;
    private String password;
    private String realName;
    private String idCardNumber;
    private int realNameOrNot;
    private Timestamp signUpTime;
    private long userCampusId;
    private String checkCode;
    private Timestamp activeTime;

    public User() {
        super();
    }

    public User(int id, String nickname, String sex, String email, String password, String realName, String idCardNumber, int realNameOrNot, Timestamp signUpTime, long userCampusId, String checkCode, Timestamp activeTime) {
        this.id = id;
        this.nickname = nickname;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.realName = realName;
        this.idCardNumber = idCardNumber;
        this.realNameOrNot = realNameOrNot;
        this.signUpTime = signUpTime;
        this.userCampusId = userCampusId;
        this.checkCode = checkCode;
        this.activeTime = activeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public int getRealNameOrNot() {
        return realNameOrNot;
    }

    public void setRealNameOrNot(int realNameOrNot) {
        this.realNameOrNot = realNameOrNot;
    }

    public Timestamp getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(Timestamp signUpTime) {
        this.signUpTime = signUpTime;
    }

    public long getUserCampusId() {
        return userCampusId;
    }

    public void setUserCampusId(long userCampusId) {
        this.userCampusId = userCampusId;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", realNameOrNot=" + realNameOrNot +
                ", signUpTime=" + signUpTime +
                ", userCampusId=" + userCampusId +
                ", checkCode='" + checkCode + '\'' +
                ", activeTime=" + activeTime +
                '}';
    }
}
