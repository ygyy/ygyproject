package com.ygy.practiceone;

public class Result<T> {

    ObjectSer obs = new ObjectSer();

    private T t;

    public Result(T t){

    }

    public Result(byte[] b,T t){
        this.t=t;
        ObjectSer.deserialize(b,t);
    }

    /**
     * 获取T对象的序列化的字节数组
     * @param t
     * @return
     */
    public byte[] getTSer(T t){
        this.t=t;
        return ObjectSer.serialize(t);
    }

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}
