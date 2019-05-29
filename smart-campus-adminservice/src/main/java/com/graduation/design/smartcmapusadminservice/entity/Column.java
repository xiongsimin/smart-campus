package com.graduation.design.smartcmapusadminservice.entity;

import java.io.Serializable;
import java.util.List;

public class Column implements Serializable {
    private int id;
    private String columnName;
    private int isOpening;
    private List<Topic> topicList;

    public Column() {
        super();
    }

    public Column(int id, String columnName, int isOpening, List<Topic> topicList) {
        this.id = id;
        this.columnName = columnName;
        this.isOpening = isOpening;
        this.topicList = topicList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getIsOpening() {
        return isOpening;
    }

    public void setIsOpening(int isOpening) {
        this.isOpening = isOpening;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @Override
    public String toString() {
        return "Column{" +
                "id=" + id +
                ", columnName='" + columnName + '\'' +
                ", isOpening=" + isOpening +
                ", topicList=" + topicList +
                '}';
    }
}
