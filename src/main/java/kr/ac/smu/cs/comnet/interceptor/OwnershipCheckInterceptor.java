package kr.ac.smu.cs.comnet.interceptor;

import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.ac.smu.cs.comnet.mapper.BoardMapper;
import kr.ac.smu.cs.comnet.mapper.UserMapper;

public class OwnershipCheckInterceptor implements HandlerInterceptor{
	@Autowired
	private BoardMapper bMapper;
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
				email=email.substring(0,email.indexOf(":"));//쿠키에서 email을 가져옴 ex:201611011
			}
		}
		if(bMapper.select(Integer.parseInt(request.getParameter("bid"))).getUid() != uMapper.selectByEmail(email).getUid()) {
			response.sendRedirect("/board/view?bid="+Integer.parseInt(request.getParameter("bid")));
			return false;//만약 자신의 게시물이 아닌 것을 접근 시에 게시물 상세조회로 돌아감
		}
		return true;
	}
}
