package com.service;

import java.util.List;

import com.model.User;


public interface UserService {
	public void initDB();
	public User load(final int id);
	public boolean register(User user);
	public User loginCheck(User user);
	public boolean update(User user);
	public User findUserByUserName(final String userName);
	public boolean delete(int id);
	public List<User> list(int pageNow,int pageSize);
	public int getAllcount();
	public String getPasswordById(int  id);
}
