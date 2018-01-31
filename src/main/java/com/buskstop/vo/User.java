package com.buskstop.vo;

import java.io.Serializable;

public class User implements Serializable{
	
	private String userId;
	private String userName;
	private String password;
	private String userAddress;
	private String userPhoneNum;
	private String email;
	
	// 탈퇴유무
	private int outCheck;
	// 관리자용.
	private String authority;
	
	public User() {}
	public User(String userId, String userName, String password, String userAddress, String userPhoneNum,
			String email) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userAddress = userAddress;
		this.userPhoneNum = userPhoneNum;
		this.email = email;
	}
	
	public User(String userId, String userName, String password, String userAddress, String userPhoneNum, String email,
			String authority) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userAddress = userAddress;
		this.userPhoneNum = userPhoneNum;
		this.email = email;
		this.authority = authority;
	}
	
	
	
	public User(String userId, String userName, String password, String userAddress, String userPhoneNum, String email,
			int outCheck, String authority) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userAddress = userAddress;
		this.userPhoneNum = userPhoneNum;
		this.email = email;
		this.outCheck = outCheck;
		this.authority = authority;
	}
	
	public int getOutCheck() {
		return outCheck;
	}
	public void setOutCheck(int outCheck) {
		this.outCheck = outCheck;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((userAddress == null) ? 0 : userAddress.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPhoneNum == null) ? 0 : userPhoneNum.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (userAddress == null) {
			if (other.userAddress != null)
				return false;
		} else if (!userAddress.equals(other.userAddress))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPhoneNum == null) {
			if (other.userPhoneNum != null)
				return false;
		} else if (!userPhoneNum.equals(other.userPhoneNum))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", Password=" + password + ", userAddress="
				+ userAddress + ", userPhoneNum=" + userPhoneNum + ", email=" + email + "]";
	}
	
	
}
