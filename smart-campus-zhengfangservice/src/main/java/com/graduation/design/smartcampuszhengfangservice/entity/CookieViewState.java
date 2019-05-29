package com.graduation.design.smartcampuszhengfangservice.entity;

import java.util.Map;

public class CookieViewState {
    private Map<String, String> cookie;//存储当前学生模拟访问时的cookie
    private String __VIEWSTATE;//

    public CookieViewState() {
        super();
    }

    public CookieViewState(Map<String, String> cookie, String __VIEWSTATE) {
        this.cookie = cookie;
        this.__VIEWSTATE = __VIEWSTATE;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    public String get__VIEWSTATE() {
        return __VIEWSTATE;
    }

    public void set__VIEWSTATE(String __VIEWSTATE) {
        this.__VIEWSTATE = __VIEWSTATE;
    }

    @Override
    public String toString() {
        return "CookieViewState{" +
                "cookie=" + cookie +
                ", __VIEWSTATE='" + __VIEWSTATE + '\'' +
                '}';
    }
}
