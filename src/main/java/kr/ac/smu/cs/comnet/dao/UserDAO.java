package kr.ac.smu.cs.comnet.dao;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserDAO {
	
	public void register(UserVO userVO);//회원가입
	
	public UserVO select(String email);
	
	public int selectUid(String email);//user_field, user_language 등록을 위해 uid 반환
}
