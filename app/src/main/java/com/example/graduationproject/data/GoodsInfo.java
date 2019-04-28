package com.example.graduationproject.data;

public class GoodsInfo {
    private int id;
    private String goodsName;
    private String goodsImage;
    private double goodsPrice;
    private int goodsNum = 1;
    private boolean isChoosed=false;

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public String getGoodsNum() {
        return String.valueOf(goodsNum);
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public void reduceNum() {
        if (goodsNum != 1) {
            goodsNum--;
        }
    }

    public void increaseNum() {
        goodsNum++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsPrice() {
        return "¥" + goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
