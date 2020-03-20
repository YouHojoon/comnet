package kr.ac.smu.cs.comnet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.HandlerInterceptor;


public class ChangePasswordInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUrl = request.getHeader("referer");
		if(request.getMethod().equals("GET"))
			return true;
		else if(request.getMethod().equals("PATCH") && (requestUrl.substring(21).equals("/findpw") 
				|| requestUrl.substring(21).contains("/mypage/info")))
				return true;
		else 
			return false;			
	}
}
