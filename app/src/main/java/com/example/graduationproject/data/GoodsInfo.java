package com.example.graduationproject.data;

public class GoodsInfo {
    private int id;
    private String name;
    private String image;
    private double price;
    private int goodsNum = 1;
    private String resume;

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

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
        return name;
    }

    public void setGoodsName(String goodsName) {
        this.name = goodsName;
    }

    public String getGoodsImage() {
        return image;
    }

    public void setGoodsImage(String goodsImage) {
        this.image = goodsImage;
    }

    public String getGoodsPrice() {
        return "¥" + price;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.price = goodsPrice;
    }
}
