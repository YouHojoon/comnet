package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.Conn_ulVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageService {
	
	public List<LanguageVO> selectList();//언어 전체 조회
	
	public List<Conn_ulVO> selectUserLanguage(int uid);//유저의 conn_ul 반환
}
