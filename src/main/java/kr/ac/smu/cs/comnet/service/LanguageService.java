package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageService {
	
	public List<LanguageVO> selectList();//언어 전체 조회
	
	public List<LanguageVO> selectBoardLanguage(int bid);//프로젝트 모집 언어 조회
}
