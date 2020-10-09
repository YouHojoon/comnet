package kr.ac.smu.cs.comnet.interceptor;

import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.ac.smu.cs.comnet.mapper.UserMapper;

public class UserInfoInterceptor implements HandlerInterceptor{
	@Autowired
	private UserMapper uMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookieList = request.getCookies();
		String email= null;
		for(Cookie c : cookieList) {
			if(c.getName().equals("remember-me")) {
				Decoder decoder = Base64.getDecoder();
				email= new String(decoder.decode(c.getValue()),"UTF-8");
				email=email.substring(0,email.indexOf(":"));//ÄíÅ°¿¡¼­ emailÀ» °¡Á®¿È ex:201611011
			}
		}
		if(email.equals(uMapper.select(Integer.parseInt(request.getParameter("uid"))).getEmail()))
				return true;
		else
			return false;
	}
}
