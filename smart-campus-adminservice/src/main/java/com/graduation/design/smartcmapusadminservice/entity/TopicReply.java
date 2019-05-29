package com.graduation.design.smartcmapusadminservice.entity;

import java.io.Serializable;
import java.util.List;

public class TopicReply implements Serializable {
    private int id;
    private String replyContent;
    private int isSubReply;
    private int topicId;
    private long userId;
    private int accusationVolume;
    private User user;
    private List<TopicReply> subTopicReplyList;

    public TopicReply() {
        super();
    }

    public TopicReply(int id, String replyContent, int isSubReply, int topicId, long userId, int accusationVolume, User user, List<TopicReply> subTopicReplyList) {
        this.id = id;
        this.replyContent = replyContent;
        this.isSubReply = isSubReply;
        this.topicId = topicId;
        this.userId = userId;
        this.accusationVolume = accusationVolume;
        this.user = user;
        this.subTopicReplyList = subTopicReplyList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public int getIsSubReply() {
        return isSubReply;
    }

    public void setIsSubReply(int isSubReply) {
        this.isSubReply = isSubReply;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getAccusationVolume() {
        return accusationVolume;
    }

    public void setAccusationVolume(int accusationVolume) {
        this.accusationVolume = accusationVolume;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TopicReply> getSubTopicReplyList() {
        return subTopicReplyList;
    }

    public void setSubTopicReplyList(List<TopicReply> subTopicReplyList) {
        this.subTopicReplyList = subTopicReplyList;
    }

    @Override
    public String toString() {
        return "TopicReply{" +
                "id=" + id +
                ", replyContent='" + replyContent + '\'' +
                ", isSubReply=" + isSubReply +
                ", topicId=" + topicId +
                ", userId=" + userId +
                ", accusationVolume=" + accusationVolume +
                ", user=" + user +
                ", subTopicReplyList=" + subTopicReplyList +
                '}';
    }
}
