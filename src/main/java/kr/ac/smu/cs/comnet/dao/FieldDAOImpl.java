package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Repository;

import kr.ac.smu.cs.comnet.mapper.FieldMapper;
import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;
import net.sf.ehcache.Ehcache;

@Repository
public class FieldDAOImpl implements FieldDAO {
	@Autowired
	private FieldMapper mapper;
	@Autowired
	EhCacheCacheManager ehcache;

	@Override
	public List<FieldVO> selectList() {
		List<FieldVO> fieldList=mapper.selectList();
		Cache fieldCache=ehcache.getCache("FieldCache");
		fieldList.stream().forEach(fieldVO -> 
		fieldCache.put(fieldVO.getFid(), fieldVO));//캐시에 fieldVO 삽입
		return fieldList;
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
