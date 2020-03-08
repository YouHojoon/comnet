package kr.ac.smu.cs.comnet.service;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserService {
	
	public void register(UserVO userVO, int[] user_field, int[] user_language);//회원가입
	
	public String auth(String email);//email 인증;
	
	public UserVO select(int uid);//User 조회
}
