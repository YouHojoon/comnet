package kr.ac.smu.cs.comnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.mapper.FieldMapper;
import kr.ac.smu.cs.comnet.vo.FieldVO;

@Service
public class FieldServiceImpl implements FieldService{
	@Autowired
	private FieldMapper mapper;
	@Autowired
	private EhCacheCacheManager ehcacheManager;
	@Override
	public List<FieldVO> selectList() {
		List<FieldVO> fieldList= mapper.selectList();
		Cache cache= ehcacheManager.getCache("FieldCache");
		fieldList.stream().forEach(fieldVO 
				-> cache.put(fieldVO.getFid(), fieldVO));//캐시에 영역 삽입
		return fieldList;
	}
}
