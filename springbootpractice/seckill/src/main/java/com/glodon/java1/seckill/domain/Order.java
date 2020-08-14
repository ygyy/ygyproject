package com.glodon.java1.seckill.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 这是MyBatis Generator自动生成的Model Class.
 * 对应的数据表是 : t_order
 * @author zhangyx-v
 * @date 2020-08-06 18:47:25
 */
public class Order extends OrderKey implements Serializable {
    /**
     * 
     */

    private Integer productId;

    /**
     *
     */
    private Integer userId;

   /* @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")*/
    private Date buyTime;

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 
     */
    private String buyStatus;

    /**
     * 
     */
    private String phone;

    private static final long serialVersionUID = 1L;

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(String buyStatus) {
        this.buyStatus = buyStatus == null ? null : buyStatus.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    @Override
    public String toString() {
        return "Order{" +
                "productId=" + productId +
                ", userId=" + userId +
                ", buyTime=" + buyTime +
                ", buyStatus='" + buyStatus + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(productId, order.productId) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(buyTime, order.buyTime) &&
                Objects.equals(buyStatus, order.buyStatus) &&
                Objects.equals(phone, order.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, userId, buyTime, buyStatus, phone);
    }
}