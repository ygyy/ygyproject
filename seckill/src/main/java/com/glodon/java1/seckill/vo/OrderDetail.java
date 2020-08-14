package com.glodon.java1.seckill.vo;

import java.util.Date;

public class OrderDetail {



    private int userId;

    private Date buyTime;

    private Double price;

    private String buyStatus;

    private String productName;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "userId='" + userId + '\'' +
                ", buyTime=" + buyTime +
                ", price=" + price +
                ", buyStatus='" + buyStatus + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(String buyStatus) {
        this.buyStatus = buyStatus;
    }
}
