package kr.ac.smu.cs.comnet.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class CheckLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookieList = request.getCookies();
		if (cookieList != null)
			for (Cookie c : cookieList) {
				if (c.getName().equals("remember-me")) {
					response.sendRedirect("/board");
					return false;
				}
			}
		return true;
	}
}
