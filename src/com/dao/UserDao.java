package com.dao;

import java.util.List;

import com.model.User;

public interface UserDao {

	public void initDB();

	public User load(int id);

	public void add(User user);

	public User findUserByUserName(final String userName);

	public void update(User user);

	public int delete(int id);

	public List<User> list(int pageNow, int pageSize);

	public int getAllcount();

	public String getPasswordById(int id);
}
