package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.smu.cs.comnet.mapper.BoardMapper;
import kr.ac.smu.cs.comnet.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	BoardMapper mapper;
	@Override
	public List<BoardVO> selectList() {
		return mapper.selectList();
	}
}
