package kr.ac.smu.cs.comnet.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserMapper {
	
	public void register(UserVO userVO);//회원가입
	
	public UserVO selectByEmail(String email);//email로 유저 찾기
	
	public UserVO select(int uid);//uid로 유저 찾기
	
	public void changePassword(@Param("email") String email, @Param("password") String password);//비밀번호 변경
	
	public void update(UserVO userVO);//유저 정보 수정
	
	public void delete(int uid);//유저 탈퇴
}
