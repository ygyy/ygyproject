package com.glodon.java1.seckill.result;

public class Result<T> {
	
	private Meta meta;
	private T data;
	
	/**
	 *  成功时候的调用
	 * */
	public static  <T>  Result<T> success(T data, Meta meta){
		return new  Result<T>(meta,data);
	}

	/**
	 *  失败时候的调用s
	 * */
	public static  <T> Result<T> error(Meta meta){
		return new  Result<T>(meta);
	}

	public Result(Meta meta, T data) {
		this.meta = meta;
		this.data = data;
	}

	public Result(Meta meta) {
		this.meta = meta;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
