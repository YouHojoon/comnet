package kr.ac.smu.cs.comnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.dao.FieldDAO;
import kr.ac.smu.cs.comnet.vo.FieldVO;

@Service
public class FieldServiceImpl implements FieldService{
	@Autowired
	FieldDAO dao;
	
	@Override
	public List<FieldVO> selectList() {
		return dao.selectList();
	}
	@Override
	public List<FieldVO> selectBoardField(int bid) {
		return dao.selectBoardField(bid);
	}
}
