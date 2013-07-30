package com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void initDB() {
		userDao.initDB();
	}

	public User load(final int id) {
		User user = userDao.load(id);
		return user;
	}

	@Override
	public User loginCheck(User user) {
		User u = userDao.findUserByUserName(user.getUserName());
		if (u != null && user.getPassword().equals(u.getPassword())) {
			return u;
		} else {
			return null;
		}
	}

	@Override
	public boolean register(User user) {
		User u = userDao.findUserByUserName(user.getUserName());
		if (null == u) {
			userDao.add(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(User user) {
		User udb = userDao.findUserByUserName(user.getUserName());
		if (udb == null || udb.getId() == user.getId()) {
			userDao.update(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean delete(int id) {
		int count = userDao.delete(id);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public User findUserByUserName(final String userName) {
		return userDao.findUserByUserName(userName);
	}

	public List<User> list(int pageNow, int pageSize) {
		List<User> list = userDao.list(pageNow, pageSize);
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	public int getAllcount() {
		return userDao.getAllcount();
	}

	public String getPasswordById(int id) {
		return userDao.getPasswordById(id);
	}

}
