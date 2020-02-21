package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import kr.ac.smu.cs.comnet.mapper.FieldMapper;
import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;

@Repository
public class FieldDAOImpl implements FieldDAO {
	@Autowired
	private FieldMapper mapper;
	@Override
	public List<FieldVO> selectList() {
		return mapper.selectList();
	}
	@Override
	public void registerUserField(int uid, int fid) {
		mapper.registerUserField(uid, fid);
	}
	@Override
	public void registerBoardField(int bid, int fid) {
		mapper.registerBoardField(bid, fid);		
	}
	@Override
	@Cacheable(cacheNames = "FieldCache")//캐시사용
	public FieldVO select(int fid) {
		return mapper.select(fid);
	}
	@Override
	public List<Conn_bfVO> selectConn_bfList() {
		return mapper.selectConn_bfList();
	}
}
