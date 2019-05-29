package com.graduation.design.smartcmapusadminservice.entity;

import java.io.Serializable;
import java.util.List;

public class GoodsType implements Serializable {
    private int id;
    private String typeName;
    private List<Goods> goodsList;

    public GoodsType() {
        super();
    }

    public GoodsType(int id, String typeName, List<Goods> goodsList) {
        this.id = id;
        this.typeName = typeName;
        this.goodsList = goodsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", goodsList=" + goodsList +
                '}';
    }
}
