package com.graduation.design.smartcmapusadminservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Goods implements Serializable {
    private int id;
    private String goodsInfo;
    private int userId;
    private double goodsPrice;
    private Timestamp time;
    private int typeId;
    private String imagePath;
    private GoodsType goodsType;
    private int accusationVolume;
    private List<GoodsReply> goodsReplyList;

    public Goods() {
        super();
    }

    public Goods(int id, String goodsInfo, int userId, double goodsPrice, Timestamp time, int typeId, String imagePath, GoodsType goodsType, int accusationVolume, List<GoodsReply> goodsReplyList) {
        this.id = id;
        this.goodsInfo = goodsInfo;
        this.userId = userId;
        this.goodsPrice = goodsPrice;
        this.time = time;
        this.typeId = typeId;
        this.imagePath = imagePath;
        this.goodsType = goodsType;
        this.accusationVolume = accusationVolume;
        this.goodsReplyList = goodsReplyList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public int getAccusationVolume() {
        return accusationVolume;
    }

    public void setAccusationVolume(int accusationVolume) {
        this.accusationVolume = accusationVolume;
    }

    public List<GoodsReply> getGoodsReplyList() {
        return goodsReplyList;
    }

    public void setGoodsReplyList(List<GoodsReply> goodsReplyList) {
        this.goodsReplyList = goodsReplyList;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsInfo='" + goodsInfo + '\'' +
                ", userId=" + userId +
                ", goodsPrice=" + goodsPrice +
                ", time=" + time +
                ", typeId=" + typeId +
                ", imagePath='" + imagePath + '\'' +
                ", goodsType=" + goodsType +
                ", accusationVolume=" + accusationVolume +
                ", goodsReplyList=" + goodsReplyList +
                '}';
    }
}
