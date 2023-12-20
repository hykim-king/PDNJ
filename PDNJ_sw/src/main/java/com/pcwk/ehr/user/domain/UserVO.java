package com.pcwk.ehr.user.domain;

import com.pcwk.ehr.cmn.DTO;

public class UserVO extends DTO {

	// C+S+x/y:대/소문자
	private String userId;// 아이디
	private String password;// 비번
	private String username;// 이름
	private String email;
	private String userTel;

	public UserVO() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public UserVO(String userId, String password, String username, String email, String userTel) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.email = email;
		this.userTel = userTel;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", password=" + password + ", username=" + username + ", email=" + email
				+ ", userTel=" + userTel + ", toString()=" + super.toString() + "]";
	}

}
