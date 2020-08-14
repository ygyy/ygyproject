package com.glodon.java1.seckill.result;

public class Meta {
	
	private int status;
	private String msg;



	//成功
	public static Meta SUCCESSING = new Meta(200, "秒杀抢购中");
	public static Meta GOODSGETSUCCESS = new Meta(200, "商品获得成功");
	public static Meta SUCCESS = new Meta(200, "SUCCESS");
	public static Meta SUCCESS_LOGIN = new Meta(200, "登录成功");
	//通用的错误码
	public static Meta ERROR_CONFIRM = new Meta(500000, "认证失败");
	public static Meta SERVER_ERROR = new Meta(500, "服务端异常");

	public static Meta BIND_ERROR = new Meta(500101, "参数校验异常：%s");
	public static Meta REQUEST_ILLEGAL = new Meta(500102, "请求非法");
	public static Meta ACCESS_LIMIT_REACHED= new Meta(500104, "访问太频繁！");
	//登录模块 5002XX
	public static Meta SESSION_ERROR = new Meta(500210, "Session不存在或者已经失效");
	public static Meta PASSWORD_EMPTY = new Meta(500211, "登录密码不能为空");
	public static Meta MOBILE_EMPTY = new Meta(500212, "手机号不能为空");
	public static Meta MOBILE_ERROR = new Meta(500213, "手机号格式错误");
	public static Meta MOBILE_NOT_EXIST = new Meta(500214, "手机号不存在");
	public static Meta ERROR_LOGIN = new Meta(500215, "用户名或密码错误");


	//商品模块 5003XX
	public static Meta PRODUCT_CODE_ERROR = new Meta(500300, "商品编号重复");
	public static Meta PRODUCT_ADD_ERROR = new Meta(500301, "添加商品失败");
	//订单模块 5004XX
	public static Meta ORDER_NOT_EXIST = new Meta(500400, "订单不存在");

	//秒杀模块 5005XX
	public static Meta MIAO_SHA_OVER = new Meta(5003, "商品已经卖光了");
	public static Meta REPEATE_MIAOSHA = new Meta(500, "不能重复秒杀");
	public static Meta MIAOSHA_FAIL = new Meta(500502, "秒杀失败");
	public static Meta TIME_OUT = new Meta(5001, "秒杀活动已结束");

	private Meta( ) {
	}

	private Meta(int status, String msg ) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	@Override
	public String toString() {
		return "Meta{" +
				"status=" + status +
				", msg='" + msg + '\'' +
				'}';
	}
}
