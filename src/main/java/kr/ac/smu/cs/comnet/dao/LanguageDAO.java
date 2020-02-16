package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageDAO {
	public List<LanguageVO> selectList();//언어 전체 조회
	
	public List<LanguageVO> selectBoardLanguage(int bid);//프로젝트 모집 언어 조회
	
	public void regiserUserLanguage(int uid, int lid);//user_language 등록
}
