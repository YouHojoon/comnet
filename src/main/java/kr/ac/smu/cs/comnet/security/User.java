package kr.ac.smu.cs.comnet.security;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import kr.ac.smu.cs.comnet.vo.UserVO;

public class User extends org.springframework.security.core.userdetails.User{

	private static final long serialVersionUID = 1L;
	
	private UserVO userVO;
	
	public User(String username,String password,Collection<? extends GrantedAuthority> authorities) {
		super(username,password,authorities);
	}
	public User(UserVO userVO) {
		super(userVO.getEmail(),userVO.getPassword(),new HashSet<GrantedAuthority>());//authority가 null이면 안되서 빈 Set을 주입
		
		this.userVO=userVO;
	}
}
