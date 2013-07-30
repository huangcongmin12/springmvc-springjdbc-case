package com.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.School;
import com.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class SchoolDaoTest {

	@Autowired
	private SchoolDao schoolDao;
	@Autowired
	private StudentDao studentDao;

	@Test
	public void testLoad() {
		School s = schoolDao.load(1);
		if (s != null) {
			System.out.println("====》:schoolId:" + s.getId() + "schoolNumber: " + s.getSchoolNumber() + "  schoolName:" + s.getSchoolName());
		} else {
			System.out.println("无记录！");
		}
	}

	@Test
	public void testList() {
		List<School> list = schoolDao.list();
		if (list.size() > 0) {
			for (School s : list) {
				System.out.println("====》:schoolId:" + s.getId() + "schoolNumber: " + s.getSchoolNumber() + "  schoolName:" + s.getSchoolName());
			}
		} else {
			System.out.println("无记录！");
		}
	}

	@Test
	public void test() {
		School s = schoolDao.load(1);
		if (s != null) {
			List<Student> list = studentDao.findBySchoolId(s.getId());
			System.out.println("====》:schoolId:" + s.getId() + "schoolNumber: " + s.getSchoolNumber() + "  schoolName:" + s.getSchoolName());
			for (Student stu : list) {
				System.out.println("===》:stuId:" + stu.getId() + "  stuName: " + stu.getStuName());
			}
		} else {
			System.out.println("无记录！");
		}
	}

}
