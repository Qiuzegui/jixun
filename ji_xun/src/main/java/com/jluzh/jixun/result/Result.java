package com.jluzh.jixun.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

public class Result<T> {
	private int code;
	private String msg;
	private T data;
	
	private Result(T data){
		this.code=0;
		this.msg="success";
		this.data=data;
	}
	private Result(CodeMsg e){
		if(e==null){
			return;
		}
		this.code=e.getCode();
		this.msg=e.getMsg();
	}
	
	/**
	 * 成功时候调用
	 * @param data
	 * @return
	 */
	public static <T> Result<T> success(T data){
		return new Result<T>(data);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Result pageSuccess(List data){
		 PageInfo pageInfo = new PageInfo(data);
	        Map<String, Object> result = new HashMap<>();
	        result.put("pageSize", pageInfo.getPageSize());
	        result.put("totalPage", pageInfo.getPages());
	        result.put("total", pageInfo.getTotal());
	        result.put("pageNum", pageInfo.getPageNum());
	        result.put("list", pageInfo.getList());
	        return new Result(result);
	}
	/**
	 * 失败时候调用
	 * @param cm
	 * @return
	 */
	public static <T> Result<T> error(CodeMsg cm){
		return new Result<T>(cm);
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
