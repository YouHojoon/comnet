package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageDAO {
	public List<LanguageVO> selectList();//언어 전체 조회
}
