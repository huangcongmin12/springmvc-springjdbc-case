package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.service.UserService;
import com.utils.Page;

@Controller
@RequestMapping(value = "/user")
public class UserComtroller {

	private final int pageSize = 12;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/free", method = RequestMethod.GET)
	public String free() {
		return "free";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginView() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerView() {
		return "register";
	}

	@RequestMapping(value = "/delete_succ", method = RequestMethod.GET)
	public String delete_succ() {
		return "delete_succ";
	}

	@RequestMapping(value = "/delete_fail", method = RequestMethod.GET)
	public String delete_fail() {
		return "delete_fail";
	}

	@RequestMapping(value = "/update_fail", method = RequestMethod.GET)
	public String update_fail() {
		return "update_fail";
	}

	@RequestMapping(value = "/update_succ", method = RequestMethod.GET)
	public String update_succ() {
		return "update_succ";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateView(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView();
		User u = userService.load(id);
		mav.setViewName("update");
		mav.addObject("user", u);
		return mav;
	}

	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, User user) {
		ModelAndView mav = new ModelAndView();
		User u = userService.loginCheck(user);
		if (null == u) {
			mav.setViewName("login");
			mav.addObject("errorMsg", "用户名或密码有误！");
			return mav;
		} else {
			request.getSession().setAttribute("user", u);
			mav.setViewName("success");
			return mav;
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(User user) {
		ModelAndView mav = new ModelAndView();
		if (userService.register(user)) {
			mav.setViewName("register_succ");
			return mav;
		} else {
			mav.setViewName("register");
			mav.addObject("errorMsg", "用户名已被占用，请更换！！");
			return mav;
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("pageNow") int pageNow) {
		ModelAndView mav = new ModelAndView();
		Page page = new Page(pageNow, this.pageSize, userService.getAllcount());
		if (page.getPageNow() > page.getTotalPage()) {
			page.setPageNow(page.getTotalPage());
		}
		List<User> list = userService.list(page.getPageNow(), page.getPageSize());
		mav.addObject("page", page);
		mav.addObject("user", list);
		mav.setViewName("userlist");
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") int id) {
		if (userService.delete(id)) {
			return "delete_succ";
		} else {
			return "delete_fail";
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(User user) {
		ModelAndView mav = new ModelAndView();
		if (userService.update(user)) {
			mav.setViewName("update_succ");
			return mav;
		} else {
			mav.addObject("id", user.getId());
			mav.setViewName("update_fail");
			return mav;
		}
	}

}
