package com.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.StudentDao;
import com.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Student load(final int id) {
		final String sql = "select * from student where id=?";
		List<Student> list = jdbcTemplate.query(sql, new Object[] { id }, new int[] { Types.INTEGER }, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int index) throws SQLException {
				Student stu = new Student();
				stu.setId(id);
				stu.setStuNumber(rs.getString("stuNumber"));
				stu.setStuName(rs.getString("stuName"));
				return stu;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<Student> findBySchoolId(final int schooId) {
		final String sql = "select * from student where school_id=?";
		List<Student> list = jdbcTemplate.query(sql, new Object[] { schooId }, new int[] { Types.INTEGER }, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int index) throws SQLException {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setStuNumber(rs.getString("stuNumber"));
				stu.setStuName(rs.getString("stuName"));
				stu.setSchoolId(rs.getInt("school_id"));
				return stu;
			}
		});
		return list;
	}

}
