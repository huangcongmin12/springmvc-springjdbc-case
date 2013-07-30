package com.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.SchoolDao;
import com.model.School;

@Repository
public class SchoolDaoImpl implements SchoolDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public School load(final int id) {
		final String sql = "select * from school where id=?";
		List<School> list = jdbcTemplate.query(sql, new Object[] { id }, new int[] { Types.INTEGER }, new RowMapper<School>() {
			public School mapRow(ResultSet rs, int index) throws SQLException {
				School school = new School();
				school.setId(rs.getInt("id"));
				school.setSchoolNumber(rs.getString("schoolNumber"));
				school.setSchoolName(rs.getString("schoolName"));
				return school;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<School> list() {
		final String sql = "select id,schoolNumber,schoolName from school";
		List<School> list = jdbcTemplate.query(sql, new RowMapper<School>() {
			public School mapRow(ResultSet rs, int i) throws SQLException {
				School school = new School();
				school.setId(rs.getInt("id"));
				school.setSchoolNumber(rs.getString("schoolNumber"));
				school.setSchoolName(rs.getString("schoolName"));
				return school;
			}

		});
		return list;
	}
}
