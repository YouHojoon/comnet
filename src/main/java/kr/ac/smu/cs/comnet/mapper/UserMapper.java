package kr.ac.smu.cs.comnet.mapper;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserMapper {
	public void register(UserVO userVO);//회원가입
	
	public UserVO select(String email);
	
}
