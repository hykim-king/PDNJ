package com.pcwk.ehr.user.domain;

import com.pcwk.ehr.cmn.DTO;

public class PostVO extends DTO {

	private int postId;
	private String title;
	private String postContent;
	private String postDate;
	private String userId;

	public PostVO() {
	}

	//
	public PostVO(int postId, String title, String postContent, String postDate, String userId) {
		super();
		this.postId = postId;
		this.title = title;
		this.postContent = postContent;
		this.postDate = postDate;
		this.userId = userId;
	}

	//
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PostVO [postId=" + postId + ", title=" + title + ", postContent=" + postContent + ", postDate="
				+ postDate + ", userId=" + userId + ", toString()=" + super.toString() + "]";
	}

}