package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.Conn_ulVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageService {
	
	public List<LanguageVO> selectList();//��� ��ü ��ȸ
	
	public List<Conn_ulVO> selectUserLanguage(int uid);//������ conn_ul ��ȯ
}
