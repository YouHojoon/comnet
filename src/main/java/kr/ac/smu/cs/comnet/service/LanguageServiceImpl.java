package kr.ac.smu.cs.comnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.mapper.LanguageMapper;
import kr.ac.smu.cs.comnet.vo.Conn_ulVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

@Service
public class LanguageServiceImpl implements LanguageService{
	@Autowired
	private LanguageMapper mapper;
	@Autowired
	private EhCacheCacheManager ehcacheManager;
	@Override
	public List<LanguageVO> selectList() {
		List<LanguageVO> languageList = mapper.selectList();
		Cache cache = ehcacheManager.getCache("LanguageCache");
		languageList.stream().forEach(languageVO 
				-> cache.put(languageVO.getLid(), languageVO));//언어를 캐시에 삽입
		return languageList;
	}
	@Override
	public List<Conn_ulVO> selectUserLanguage(int uid) {
		return mapper.selectUserLanguage(uid);
	}
}
