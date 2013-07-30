package com.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class StudentDaoTest {

	@Autowired
	private StudentDao studentDao;

	@Test
	public void testLoad() {
		Student stu = new Student();
		stu = studentDao.load(1);
		if (stu != null) {
			System.out.println("====》:stuId:" + stu.getId() + "  stuName:" + stu.getStuName());
		} else {
			System.out.println("id不存在！");
		}
	}

	@Test
	public void testList() {
		List<Student> list = studentDao.findBySchoolId(1);
		if (list.size() > 0) {
			for (Student stu : list) {
				System.out.println("====》:stuId:" + stu.getId() + "  stuName:" + stu.getStuName());
			}
		} else {
			System.out.println("无记录！");
		}
	}
}
