package kr.ac.smu.cs.comnet.service;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserService {
	
	public void register(UserVO userVO, int[] userField, int[] userLanguage);//ȸ������
	
	public String auth(String email);//email ����;
	
	public UserVO select(int uid);//User ��ȸ
}
