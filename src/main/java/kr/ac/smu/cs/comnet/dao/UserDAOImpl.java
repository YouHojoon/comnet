package kr.ac.smu.cs.comnet.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.smu.cs.comnet.mapper.UserMapper;
import kr.ac.smu.cs.comnet.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private UserMapper mapper;
	@Override
	public void register(UserVO userVO) {
		mapper.register(userVO);
	}
	@Override
	public UserVO select(String email) {
		return mapper.select(email);
	}
	
}
