package com.zx.seckill.result;

/**
 * @Description:返回结果
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */
public class Result<T> {
	
	private int code;
	private String msg;
	private T data;
    // 返回成功的消息体
	public static  <T> Result<T> success(T data){
		return new Result<T>(data);
	}
    // 返回失败的消息体
	public static  <T> Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
	}
	// 成功时的消息构造函数
	private Result(T data) {
	    this.code = 0;
	    this.msg = "success";
		this.data = data;
	}
    // 备用
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
    // 失败时的消息构造函数
	private Result(CodeMsg codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();  // 设置失败的具体code
			this.msg = codeMsg.getMsg();    // 设置失败的具体msg
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
