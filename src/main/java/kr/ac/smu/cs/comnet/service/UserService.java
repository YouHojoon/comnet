package kr.ac.smu.cs.comnet.service;

import org.springframework.http.HttpStatus;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserService {
	
	public void register(UserVO userVO, int[] user_field, int[] user_language);//ȸ������
	
	public String auth(String email);//email ����;
}
