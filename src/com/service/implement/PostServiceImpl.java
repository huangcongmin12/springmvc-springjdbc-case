package com.service.implement;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PostDao;
import com.model.Post;
import com.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	public void add(Post post) {
		postDao.add(post);
	}

	public Post load(int id) {
		Post post = postDao.load(id);
		return post;
	}

	public void loadByStream(final int id, final OutputStream os) {
		postDao.loadByStream(id, os);
	}
}
