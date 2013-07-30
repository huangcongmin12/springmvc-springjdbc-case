package com.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.User;
import com.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testInitDB() {
		userService.initDB();
	}

	@Test
	public void testLoad() {
		User user = new User();
		user = userService.load(3);
		if (user != null) {
			System.out.println("------------id=" + user.getId() + ",userName=" + user.getUserName() + ",password=" + user.getPassword()
					+ "--------------------------");
		} else {
			System.out.println("id不存在！");
		}
	}

	@Test
	public void testLoginCheck() {
		User user = new User();
		user.setUserName("manager");
		user.setPassword("123456");
		if (null != userService.loginCheck(user))
			System.out.println("------OK!!-----");
		else
			System.out.println("------Sorry!!-----");
	}

	@Test
	public void testRegister() {
		User user = new User();
		user.setUserName("managerqwq");
		user.setPassword("123");
		System.out.println(userService.register(user));
	}

	@Test
	public void testUpdate() {
		User user = new User();
		user.setId(2);
		user.setUserName("刘亦菲2");
		user.setPassword("123456");
		System.out.println("++++++++" + userService.update(user));
	}

	@Test
	public void testDelete() {
		userService.delete(11);
	}

	@Test
	public void testList() {
		List<User> list = userService.list(1, 4);
		if (list != null && list.size() > 0) {
			for (User user : list) {
				System.out
						.println("------------id=" + user.getId() + ",userName=" + user.getUserName() + ",password=" + user.getPassword() + "---------------");
			}
		} else {
			System.out.println("无记录！");
		}
	}

	@Test
	public void testFindByUserName() {
		User user = userService.findUserByUserName("admin1212");
		if (user != null) {
			System.out.println("------------id=" + user.getId() + ",userName=" + user.getUserName() + ",password=" + user.getPassword() + "---------------");
		} else {
			System.out.println("用户名不存在！");
		}
	}

	@Test
	public void testGetAllCount() {
		System.out.println("===========Users Count  :  " + userService.getAllcount());
	}

	@Test
	public void testGetPasswordByUname() {
		System.out.println("===========password :  " + userService.getPasswordById(1));
	}

}
