package kr.ac.smu.cs.comnet.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserMapper {
	
	public void register(UserVO userVO);//회원가입
	
	@Cacheable("loginUserCache")
	public UserVO selectByEmail(String email);//email로 유저 찾기
	
	@Cacheable("UserCache")//지원자 조회를 할 때 중복 조회를 피하기 위해 캐시 사용
	public UserVO select(int uid);//uid로 유저 찾기
	
	public void changePassword(@Param("email") String email, @Param("password") String password);//비밀번호 변경
	
	public void update(UserVO userVO);//유저 정보 수정
	
	public void delete(int uid);//유저 탈퇴
	
}
