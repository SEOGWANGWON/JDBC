package com.kh.userVODAO;

import java.util.Date;

public class UserVO {
	private int userId; // ����� ID
	private String username; // ����� �̸�
	private String email;
	private Date regDate; // ���� ��¥
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
