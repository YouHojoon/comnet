package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import kr.ac.smu.cs.comnet.mapper.LanguageMapper;
import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

@Repository
public class LanguageDAOImpl implements LanguageDAO{
	@Autowired
	private LanguageMapper mapper;
	
	@Override
	public List<LanguageVO> selectList() {
		return mapper.selectList();
	}
	@Override
	public void regiserUserLanguage(int uid, int lid) {
		mapper.registerUserLanguage(uid, lid);
	}
	@Override
	public void regiserBoardLanguage(int bid, int lid) {
		mapper.registerBoardLanguage(bid, lid);
	}
	@Override
	@Cacheable(cacheNames = "LanguageCache")//캐시사용
	public LanguageVO select(int lid) {
		return mapper.select(lid);
	}
	@Override
	public List<Conn_blVO> selectConn_blList() {
		return mapper.selectConn_blList();
	}
}
