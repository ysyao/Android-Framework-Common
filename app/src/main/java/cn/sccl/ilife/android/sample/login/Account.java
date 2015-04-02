package cn.sccl.ilife.android.sample.login;

import com.google.gson.annotations.SerializedName;

public class Account {
	@SerializedName("res")
	private String res;
	@SerializedName("user_name")
	private String userName;
	@SerializedName("user_id")
	private String userId;
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
