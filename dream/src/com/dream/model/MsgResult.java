package com.dream.model;

import java.util.Map;

public class MsgResult {
	private Boolean success;
	private String msg;
	private Object data;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public MsgResult() {
		// TODO Auto-generated constructor stub
	}
	
	public void setErrorMsg(String msg) {
		this.success = false;
		this.msg = msg;
	}
	
	public void setSuccessMsg(String msg) {
		this.success = true;
		this.msg = msg;
	}
	
	public void setSuccessMsg(String msg,Object data) {
		this.success = true;
		this.msg = msg;
		this.data=data;
	}
	
}
