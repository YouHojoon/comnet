package kr.ac.smu.cs.comnet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.ac.smu.cs.comnet.dao.UserDAO;
import kr.ac.smu.cs.comnet.vo.UserVO;

public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user=dao.select(username);
		return user == null ? null : new User(user);
	}
}
