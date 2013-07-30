package com.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StudentDao;
import com.model.Student;
import com.service.StudentService;

@Service
public class StudnetServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	public Student load(final int id) {
		return studentDao.load(id);
	}

}
