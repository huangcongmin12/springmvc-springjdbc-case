package com.dao;

import java.io.OutputStream;

import com.model.Post;

public interface PostDao {

	public void add(Post post);

	public Post load(final int id);

	public void loadByStream(final int id, final OutputStream os);

}
