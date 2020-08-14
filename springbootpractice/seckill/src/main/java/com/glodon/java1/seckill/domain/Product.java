package com.glodon.java1.seckill.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 这是MyBatis Generator自动生成的Model Class.
 * 对应的数据表是 : s_product
 * @author zhangyx-v
 * @date 2020-08-06 18:59:16
 */
public class Product implements Serializable {
    /**
     * 
     */
    private Integer productId;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String productName;

    /**
     * 
     */
    private Double price;

    /**
     * 
     */
    private Double priceSpike;

    /**
     * 
     */
    /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")*/
    private Date startTime;

    /**
     * 
     */
    /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")*/
    private Date endTime;

    /**
     * 
     */
    private Integer count;

    /**
     * 
     */
    private String productStatus;

    /**
     * 
     */
    private String pictureSrc;

    /**
     * 
     */
    private String description;
    /**
     * ygy
     * 2020/8/7
     * 当前库存
     */
    private Integer curCount;

    public Integer getCurCount() {
        return curCount;
    }

    public void setCurCount(Integer curCount) {
        this.curCount = curCount;
    }

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceSpike() {
        return priceSpike;
    }

    public void setPriceSpike(Double priceSpike) {
        this.priceSpike = priceSpike;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus == null ? null : productStatus.trim();
    }

    public String getPictureSrc() {
        return pictureSrc;
    }

    public void setPictureSrc(String pictureSrc) {
        this.pictureSrc = pictureSrc == null ? null : pictureSrc.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", code=").append(code);
        sb.append(", productName=").append(productName);
        sb.append(", price=").append(price);
        sb.append(", priceSpike=").append(priceSpike);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", count=").append(count);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", pictureSrc=").append(pictureSrc);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }
}