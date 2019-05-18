package com.zx.miaosha.result;

public class Result<T> {
	
	private int code;		// 状态码
	private String msg;		// 描述信息
	private T data;			// 其他数据
	
	/**
	 *  成功时调用
	 * */
	public static  <T> Result<T> success(T data){
		return new Result<T>(data);
	}
	/**
	 *  失败时调用
	 * */
	public static  <T> Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
	}
	private Result(T data) {
		this.data = data;
	}
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private Result(CodeMsg codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
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
