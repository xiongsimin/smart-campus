package com.graduation.design.smartcampuszhengfangservice.util;

import java.io.Serializable;
import java.util.Map;

public class ZhengFang implements Serializable {
    private String url;//学校教务系统ip(包括http://或https://)
    private String stuId;//学生id
    private String stuPwd;//学生密码

    public ZhengFang() {
        super();
    }

    public ZhengFang(String url, String stuId, String stuPwd) {
        this.url = url;
        this.stuId = stuId;
        this.stuPwd = stuPwd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuPwd() {
        return stuPwd;
    }

    public void setStuPwd(String stuPwd) {
        this.stuPwd = stuPwd;
    }

    @Override
    public String toString() {
        return "ZhengFang{" +
                "url='" + url + '\'' +
                ", stuId='" + stuId + '\'' +
                ", stuPwd='" + stuPwd + '\'' +
                '}';
    }
}
