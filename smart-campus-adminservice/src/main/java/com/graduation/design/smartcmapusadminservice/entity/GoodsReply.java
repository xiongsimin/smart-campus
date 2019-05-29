package com.graduation.design.smartcmapusadminservice.entity;

import java.io.Serializable;
import java.util.List;

public class GoodsReply implements Serializable {
    private int id;
    private String replyContent;
    private int is_sub_reply;
    private int replyId;
    private int userId;
    private int goodsId;
    private int accusationVolume;
    private User user;
    private List<GoodsReply> subGoodsReply;

    public GoodsReply() {
        super();
    }

    public GoodsReply(int id, String replyContent, int is_sub_reply, int replyId, int userId, int goodsId, int accusationVolume, User user, List<GoodsReply> subGoodsReply) {
        this.id = id;
        this.replyContent = replyContent;
        this.is_sub_reply = is_sub_reply;
        this.replyId = replyId;
        this.userId = userId;
        this.goodsId = goodsId;
        this.accusationVolume = accusationVolume;
        this.user = user;
        this.subGoodsReply = subGoodsReply;
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

    public int getIs_sub_reply() {
        return is_sub_reply;
    }

    public void setIs_sub_reply(int is_sub_reply) {
        this.is_sub_reply = is_sub_reply;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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

    public List<GoodsReply> getSubGoodsReply() {
        return subGoodsReply;
    }

    public void setSubGoodsReply(List<GoodsReply> subGoodsReply) {
        this.subGoodsReply = subGoodsReply;
    }

    @Override
    public String toString() {
        return "GoodsReply{" +
                "id=" + id +
                ", replyContent='" + replyContent + '\'' +
                ", is_sub_reply=" + is_sub_reply +
                ", replyId=" + replyId +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", accusationVolume=" + accusationVolume +
                ", user=" + user +
                ", subGoodsReply=" + subGoodsReply +
                '}';
    }
}
