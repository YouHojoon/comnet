package kr.ac.smu.cs.comnet.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.UserVO;

public interface UserMapper {
	
	public void register(UserVO userVO);//ȸ������
	
	@Cacheable("loginUserCache")
	public UserVO selectByEmail(String email);//email�� ���� ã��
	
	@Cacheable("UserCache")//������ ��ȸ�� �� �� �ߺ� ��ȸ�� ���ϱ� ���� ĳ�� ���
	public UserVO select(int uid);//uid�� ���� ã��
	
	public void changePassword(@Param("email") String email, @Param("password") String password);//��й�ȣ ����
	
	public void update(UserVO userVO);//���� ���� ����
	
	public void delete(int uid);//���� Ż��
	
}
