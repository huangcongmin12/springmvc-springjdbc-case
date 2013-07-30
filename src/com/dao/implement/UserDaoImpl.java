package com.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 用户建表
	public void initDB() {
		String sql = "create table tb_user(id int primary key,uname varchar(60),pwd varchar(60))";
		jdbcTemplate.execute(sql);
	}

	// 加载用户
	public User load(final int id) {
		String sqlStr = "select * from user where id=?";
		List<User> list = jdbcTemplate.query(sqlStr, new Object[] { id }, new int[] { Types.INTEGER }, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUserName(rs.getString("uname"));
				u.setPassword(rs.getString("pwd"));
				return u;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	// 按用户名查找
	public User findUserByUserName(final String userName) {
		String sqlStr = "select id,uname,pwd from user where uname=?";
		List<User> list = jdbcTemplate.query(sqlStr, new Object[] { userName }, new int[] { Types.VARCHAR }, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUserName(rs.getString("uname"));
				u.setPassword(rs.getString("pwd"));
				return u;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	// 新增用户
	public void add(User user) {
		final String sqlStr = "insert into user(uname,pwd) values(?,?)";
		Object[] params = new Object[] { user.getUserName(), user.getPassword() };
		// 显式指定每个占位符所对应的字段类型，保证类型安全
		jdbcTemplate.update(sqlStr, params, new int[] { Types.VARCHAR, Types.VARCHAR });
	}

	// 用户更新
	public void update(User user) {
		String sqlStr = "update user set uname=?,pwd=? where id=?";
		Object[] params = new Object[] { user.getUserName(), user.getPassword(), user.getId() };
		jdbcTemplate.update(sqlStr, params, new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER });
	}

	// 删除用户
	public int delete(int id) {
		String sqlStr = "delete from user where id=?";
		return jdbcTemplate.update(sqlStr, new Object[] { id }, new int[] { Types.INTEGER });
	}

	// 加载用户列表
	public List<User> list(int pageNow, int pageSize) {
		String sqlStr = "select * from user limit ?,?";
		List<User> list = jdbcTemplate.query(sqlStr, new Object[] { (pageNow - 1) * pageSize, pageSize }, new int[] { Types.INTEGER, Types.INTEGER },
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int index) throws SQLException {
						User u = new User();
						u.setId(rs.getInt(1));
						u.setUserName(rs.getString(2));
						u.setPassword(rs.getString(3));
						return u;
					}
				});
		return list;
	}

	// 获取用户总数量
	public int getAllcount() {
		String sqlStr = "select count(*) from user";
		return jdbcTemplate.queryForInt(sqlStr);
	}

	// 通过用户名查找密码
	public String getPasswordById(int id) {
		String sqlStr = "select pwd from user where id=?";
		String pwd = jdbcTemplate.queryForObject(sqlStr, new Object[] { id }, new int[] { Types.INTEGER }, String.class);
		return pwd;
	}

}
