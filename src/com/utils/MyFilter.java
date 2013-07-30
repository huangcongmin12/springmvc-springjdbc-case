package com.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.model.User;

public class MyFilter implements Filter {

	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	// 不需要登录即可访问的URI资源
	private static final String[] INHERENT_ESCAPE_URIS = { "index.jsp", "/user/login.html", "/user/loginOut.html", "/user/index.html", "/user/register.html",
			"/user/free.html" };

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		// 保证该过滤器在一次请求中只被调用一次
		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			filterChain.doFilter(request, response);
		} else {
			// 设置过滤标识，防止一次请求多次过滤
			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			User userOfSession = (User) httpRequest.getSession().getAttribute("user");
			// 用户未登录, 且当前URI资源需要登录才能访问
			if (userOfSession == null && !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
				request.setAttribute("errorMsg", "sorry！ 请先登录！");
				request.getRequestDispatcher("login.html").forward(request, response); // 转发到登录页面
			}
			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 当前URI资源是否需要登录才能访问 返回true，无需登录即可访问 返回false，需登录才能访问
	 * 
	 * @param requestURI
	 * @param request
	 * @return
	 */
	private boolean isURILogin(String requestURI, HttpServletRequest request) {
		if (request.getContextPath().equalsIgnoreCase(requestURI) || (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
			return true;
		for (String uri : INHERENT_ESCAPE_URIS) {
			// 字符串的IndexOf()方法搜索在该字符串上是否出现了作为参数传递的字符串,
			// 如果找到字符串,则返回字符的起始位置 (0表示第一个字符,1表示第二个字符依此类推)如果说没有找到则返回 -1
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}
}
