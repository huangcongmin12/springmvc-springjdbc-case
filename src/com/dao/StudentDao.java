package com.dao;

import java.util.List;

import com.model.Student;

public interface StudentDao {

	public Student load(final int id);

	public List<Student> findBySchoolId(final int schooId);

}
