package com.dao;

import java.util.List;

import com.model.School;

public interface SchoolDao {

	public School load(final int id);

	public List<School> list();
}
