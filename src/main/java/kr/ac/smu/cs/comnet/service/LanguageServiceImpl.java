package kr.ac.smu.cs.comnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.dao.LanguageDAO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

@Service
public class LanguageServiceImpl implements LanguageService{
	@Autowired
	private LanguageDAO dao;
	@Autowired
	private EhCacheCacheManager ehcacheManager;
	@Override
	public List<LanguageVO> selectList() {
		List<LanguageVO> languageList = dao.selectList();
		Cache cache = ehcacheManager.getCache("LanguageCache");
		languageList.stream().forEach(languageVO 
				-> cache.put(languageVO.getLid(), languageVO));//언어를 캐시에 삽입
		return languageList;
	}
	
}
