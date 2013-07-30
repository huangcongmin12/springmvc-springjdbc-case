package com.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Post implements Serializable {

	private int id;
	private String postText;
	private byte[] postAttach;
	private Date postTime;

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public byte[] getPostAttach() {
		return postAttach;
	}

	public void setPostAttach(byte[] postAttach) {
		this.postAttach = postAttach;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
