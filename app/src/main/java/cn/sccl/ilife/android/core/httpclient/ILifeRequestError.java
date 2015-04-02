package cn.sccl.ilife.android.core.httpclient;

import com.google.gson.annotations.SerializedName;

public class ILifeRequestError {
	@SerializedName("message")
	private String message;
	@SerializedName("code")
	private String code;
	@SerializedName("request")
	private String request;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}

}
