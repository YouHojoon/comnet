package kr.ac.smu.cs.comnet.service;


import java.util.List;

import kr.ac.smu.cs.comnet.dto.UserDTO;
import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserService {
	
	public void register(UserVO userVO, int[] userField, int[] userLanguage);//ȸ������
	
	public String auth(String email, String requestUrl);//email ����;
	
	public UserVO select(int uid);//User ��ȸ
	
	public void changePassword(String email, String password);//��й�ȣ ����
	
	public void update(UserVO userVO, List<Integer> userField, List<Integer> userLanguage);//���� ���� ����

	public void delete(int uid);//���� Ż��

	public UserDTO userDetail(int uid);//���� �� ����
	
}
