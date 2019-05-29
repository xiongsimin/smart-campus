package com.graduation.design.smartcmapusadminservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Topic implements Serializable {
    private int id;
    private String topicTitle;
    private String topicContent;
    private int columnId;
    private Timestamp time;
    private long userId;
    private String imagePath;
    private Column column;
    private User user;
    private int accusationVolume;
    private List<TopicReply> subTopicList;

    public Topic() {
        super();
    }

    public Topic(int id, String topicTitle, String topicContent, int columnId, Timestamp time, long userId, String imagePath, Column column, User user, int accusationVolume, List<TopicReply> subTopicList) {
        this.id = id;
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
        this.columnId = columnId;
        this.time = time;
        this.userId = userId;
        this.imagePath = imagePath;
        this.column = column;
        this.user = user;
        this.accusationVolume = accusationVolume;
        this.subTopicList = subTopicList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAccusationVolume() {
        return accusationVolume;
    }

    public void setAccusationVolume(int accusationVolume) {
        this.accusationVolume = accusationVolume;
    }

    public List<TopicReply> getSubTopicList() {
        return subTopicList;
    }

    public void setSubTopicList(List<TopicReply> subTopicList) {
        this.subTopicList = subTopicList;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicContent='" + topicContent + '\'' +
                ", columnId=" + columnId +
                ", time=" + time +
                ", userId=" + userId +
                ", imagePath='" + imagePath + '\'' +
                ", column=" + column +
                ", user=" + user +
                ", accusationVolume=" + accusationVolume +
                ", subTopicList=" + subTopicList +
                '}';
    }
}
