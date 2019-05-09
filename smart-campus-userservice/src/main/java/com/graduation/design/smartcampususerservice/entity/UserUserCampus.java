package com.graduation.design.smartcampususerservice.entity;

import java.io.Serializable;

/**
 * 该类包装了User和UserCampus，用于接收请求数据（@RequestBody只能获取一次I/O）
 */
public class UserUserCampus implements Serializable {
    private User user;
    private UserCampus userCampus;

    public UserUserCampus() {
        super();
    }

    public UserUserCampus(User user, UserCampus userCampus) {
        this.user = user;
        this.userCampus = userCampus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserCampus getUserCampus() {
        return userCampus;
    }

    public void setUserCampus(UserCampus userCampus) {
        this.userCampus = userCampus;
    }

    @Override
    public String toString() {
        return "UserUserCampus{" +
                "user=" + user +
                ", userCampus=" + userCampus +
                '}';
    }
}
