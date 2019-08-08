package com.jluzh.search.result;

public class CodeMsg {
	private int code;
	private String Msg;
	
	//通用的异常
	public static CodeMsg SUCCESS = new CodeMsg(0,"success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
	//登录异常 5002XX
	public static CodeMsg LOGIN_FAIL = new CodeMsg(500201,"登录失败");
	//商品异常 5003XX
	
	//订单异常 5004XX	
	
	//秒杀异常 5005XX
	
	private CodeMsg(int code, String Msg) {
		this.code = code;
		this.Msg = Msg ;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		this.Msg = msg;
	}
	
	
}
