package com.service;

import java.io.OutputStream;

import com.model.Post;

public interface PostService {
	
	public void add(Post post);

	public Post load(int id);

	public void loadByStream(final int id, final OutputStream os);
}
