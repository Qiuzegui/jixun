package com.jluzh.jixun.result;

public class CodeMsg {
	private int code;
	private String Msg;
	
	//通用的异常
	public static CodeMsg SUCCESS = new CodeMsg(0,"success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
	//登录异常 5002XX
	public static CodeMsg LOGIN_FAIL = new CodeMsg(500201,"登录失败");
	//商品异常 5003XX
	public static CodeMsg NO_ENOUGH = new CodeMsg(500301,"库存不足");
	public static CodeMsg NO_FOUND = new CodeMsg(500302,"无此商品");
	//加入购物车异常 5004XX
	public static CodeMsg ADDCAR_FAIL = new CodeMsg(500401, "加入购物车异常");
	public static CodeMsg MODCAR_FAIL = new CodeMsg(500402, "修改购物车商品异常");
	//订单异常 5005XX	
	public static CodeMsg EMPTY_TIMEOUT = new CodeMsg(500501,"没有超时订单");
	public static CodeMsg MODIFY_ERROR = new CodeMsg(500501,"修改失败");
	
	public static CodeMsg PUBLISH_ERROR = new CodeMsg(500601,"发布失败");
	public static CodeMsg DESTORY_ERROR = new CodeMsg(500602,"删除失败");
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
