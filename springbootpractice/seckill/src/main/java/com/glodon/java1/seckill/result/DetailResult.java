package com.glodon.java1.seckill.result;

import java.util.List;

/**
 * ygy
 * 2020/8/9
 * 返回商品详情
 * @param <T>
 */
public class DetailResult<T> extends Result<T>{

    private List<String> phoneList;

    public DetailResult( T data, Meta meta,List<String> phoneList) {
        super(meta, data);
        this.phoneList = phoneList;
    }

    public DetailResult(Meta meta, List<String> phoneList) {
        super(meta);
        this.phoneList = phoneList;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }
}
