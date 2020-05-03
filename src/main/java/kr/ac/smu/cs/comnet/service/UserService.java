package kr.ac.smu.cs.comnet.service;


import java.util.List;

import kr.ac.smu.cs.comnet.dto.UserDTO;
import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserService {
	
	public void register(UserVO userVO, int[] userField, int[] userLanguage);//회원가입
	
	public String auth(String email, String requestUrl);//email 인증;
	
	public UserVO select(int uid);//User 조회
	
	public void changePassword(String email, String password);//비밀번호 변경
	
	public void update(UserVO userVO, List<Integer> userField, List<Integer> userLanguage);//유저 정보 수정

	public void delete(int uid);//유저 탈퇴

	public UserDTO userDetail(int uid);//유저 상세 정보
	
}
