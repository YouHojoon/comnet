package kr.ac.smu.cs.comnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.dao.UserDAO;
import kr.ac.smu.cs.comnet.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO dao;
	
	@Override
	public void register(UserVO userVO) {
		dao.register(userVO);
	}
}
