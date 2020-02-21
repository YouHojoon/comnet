package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageMapper {
	
	public List<LanguageVO> selectList();//언어 전체 조회
	
	public List<LanguageVO> selectBoardLanguage(int bid);//프로젝트 언어 조회
	
	public void registerUserLanguage(@Param("uid") int uid, @Param("lid") int lid);//user_language 등록
	
	public void registerBoardLanguage(@Param("bid") int bid, @Param("lid") int lid);//board_language 등록
}
