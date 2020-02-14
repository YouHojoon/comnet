package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.smu.cs.comnet.mapper.FieldMapper;
import kr.ac.smu.cs.comnet.vo.FieldVO;

@Repository
public class FieldDAOImpl implements FieldDAO {
	@Autowired
	private FieldMapper mapper;
	@Override
	public List<FieldVO> selectList() {
		return mapper.selectList();
	}
}
