package kr.ac.smu.cs.comnet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.ac.smu.cs.comnet.mapper.BoardMapper;
import kr.ac.smu.cs.comnet.vo.Conn_bvVO;
import kr.ac.smu.cs.comnet.vo.Conn_ubVO;

public class CheckPartnerOrVolunteerInterceptor implements HandlerInterceptor{
	@Autowired
	private BoardMapper mapper;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getMethod().equals("POST")) {
			int bid=Integer.valueOf(request.getParameter("bid"));
			int vid=Integer.valueOf(request.getParameter("vid"));
			Conn_ubVO conn_ubVO= mapper.selectConn_ub(bid,vid);
			Conn_bvVO conn_bvVO= mapper.selectConn_bv(bid, vid);
			if(conn_bvVO!=null||conn_ubVO!=null)
				return false;
		}
		
		return true;
	}
}
