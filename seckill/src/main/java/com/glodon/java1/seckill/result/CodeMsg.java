package com.glodon.java1.seckill.result;

public class CodeMsg{
	
	private int code;
	private String msg;
	
	//通用的错误码
	public static com.glodon.java1.seckill.result.CodeMsg SUCCESS = new com.glodon.java1.seckill.result.CodeMsg (0, "success");
	public static com.glodon.java1.seckill.result.CodeMsg SERVER_ERROR = new com.glodon.java1.seckill.result.CodeMsg(500100, "服务端异常");
	public static com.glodon.java1.seckill.result.CodeMsg BIND_ERROR = new com.glodon.java1.seckill.result.CodeMsg(500101, "参数校验异常：%s");
	public static com.glodon.java1.seckill.result.CodeMsg REQUEST_ILLEGAL = new com.glodon.java1.seckill.result.CodeMsg(500102, "请求非法");
	public static com.glodon.java1.seckill.result.CodeMsg ACCESS_LIMIT_REACHED= new com.glodon.java1.seckill.result.CodeMsg(500104, "访问太频繁！");
	//登录模块 5002XX
	public static com.glodon.java1.seckill.result.CodeMsg SESSION_ERROR = new com.glodon.java1.seckill.result.CodeMsg(500210, "Session不存在或者已经失效");
	public static com.glodon.java1.seckill.result.CodeMsg PASSWORD_EMPTY = new com.glodon.java1.seckill.result.CodeMsg(500211, "登录密码不能为空");
	public static com.glodon.java1.seckill.result.CodeMsg MOBILE_EMPTY = new com.glodon.java1.seckill.result.CodeMsg(500212, "手机号不能为空");
	public static com.glodon.java1.seckill.result.CodeMsg  MOBILE_ERROR = new com.glodon.java1.seckill.result.CodeMsg(500213, "手机号格式错误");
	public static com.glodon.java1.seckill.result.CodeMsg MOBILE_NOT_EXIST = new com.glodon.java1.seckill.result.CodeMsg(500214, "手机号不存在");
	public static com.glodon.java1.seckill.result.CodeMsg PASSWORD_ERROR = new com.glodon.java1.seckill.result.CodeMsg(500215, "密码错误");


	//商品模块 5003XX


	//订单模块 5004XX
	public static com.glodon.java1.seckill.result.CodeMsg ORDER_NOT_EXIST = new com.glodon.java1.seckill.result.CodeMsg(500400, "订单不存在");

	//秒杀模块 5005XX
	public static com.glodon.java1.seckill.result.CodeMsg MIAO_SHA_OVER = new com.glodon.java1.seckill.result.CodeMsg(500500, "商品已经秒杀完毕");
	public static com.glodon.java1.seckill.result.CodeMsg REPEATE_MIAOSHA = new com.glodon.java1.seckill.result.CodeMsg(500501, "不能重复秒杀");
	public static com.glodon.java1.seckill.result.CodeMsg MIAOSHA_FAIL = new com.glodon.java1.seckill.result.CodeMsg(500502, "秒杀失败");


	private CodeMsg( ) {
	}

	private CodeMsg(int code, String msg ) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public com.glodon.java1.seckill.result.CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new com.glodon.java1.seckill.result.CodeMsg(code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	
}
