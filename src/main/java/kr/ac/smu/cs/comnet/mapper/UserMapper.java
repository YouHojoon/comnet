package kr.ac.smu.cs.comnet.mapper;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserMapper {
	public void register(UserVO userVO);//ȸ������
	
	public UserVO select(String email);
	
	public int selectUid(String email);//user_field, user_language ����� ���� uid ��ȯ
}
