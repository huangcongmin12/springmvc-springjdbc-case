package com.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class StudentServiceTest {

	@Autowired
	private StudentService studentService;

	@Test
	public void testLoad() {
		Student stu = new Student();
		stu = studentService.load(3);
		if (stu != null) {
			System.out.println("===》:stuId:" + stu.getId() + "  stuName: " + stu.getStuName());
		} else {
			System.out.println("id不存在！！");
		}
	}

}
