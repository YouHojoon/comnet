package kr.ac.smu.cs.comnet.dao;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserDAO {
	
	public void register(UserVO userVO);//ȸ������
	
	public UserVO select(String email);
	
}
