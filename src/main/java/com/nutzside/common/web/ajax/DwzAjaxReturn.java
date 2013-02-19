package com.nutzside.common.web.ajax;

public class DwzAjaxReturn {
	
	int statusCode;
	String message;
	String navTabId;
	String callbackType;
	Object data;


	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public Object getData() {
		return data;
	}

	public DwzAjaxReturn setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public DwzAjaxReturn setMessage(String message) {
		this.message = message;
		return this;
	}

	public DwzAjaxReturn setNavTabId(String navTabId) {
		this.navTabId = navTabId;
		return this;
	}

	public DwzAjaxReturn setCallbackType(String callbackType) {
		this.callbackType = callbackType;
		return this;
	}

	public DwzAjaxReturn setData(Object data) {
		this.data = data;
		return this;
	}

}