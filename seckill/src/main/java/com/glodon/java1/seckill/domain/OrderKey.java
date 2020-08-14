package com.glodon.java1.seckill.domain;

import java.io.Serializable;

/**
 * 这是MyBatis Generator自动生成的Model Class.
 * 对应的数据表是 : t_order
 * @author zhangyx-v
 * @date 2020-08-06 18:47:25
 */
public class OrderKey implements Serializable {
    /**
     * 
     */
    private Integer productId;

    /**
     * 
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}