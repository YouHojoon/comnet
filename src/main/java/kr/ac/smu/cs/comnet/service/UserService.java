package kr.ac.smu.cs.comnet.service;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserService {
	
	public void register(UserVO userVO, int[] userField, int[] userLanguage);//회원가입
	
	public String auth(String email, String requestUrl);//email 인증;
	
	public UserVO select(int uid);//User 조회
	
	public void changePassword(String email, String password);//비밀번호 변경
}
