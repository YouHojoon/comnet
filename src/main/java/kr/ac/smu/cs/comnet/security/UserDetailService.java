package kr.ac.smu.cs.comnet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.ac.smu.cs.comnet.mapper.UserMapper;
import kr.ac.smu.cs.comnet.vo.UserVO;

public class UserDetailService implements UserDetailsService{
	@Autowired
	private UserMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user=mapper.select(username);
		return user == null ? null : new User(user);
	}
}
