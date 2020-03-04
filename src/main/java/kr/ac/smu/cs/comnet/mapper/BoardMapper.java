package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> selectList();//프로젝트 전체 조회
	
	public void register(BoardVO boardVO);//프로젝트 등록
	
	public int select(String reg_date);//board_field, board_langueage 등록할때 bid가 필요해서
	
	public List<BoardVO> selectSuitableList(@Param("selectFieldList") List<Integer> selectFieldList, 
			@Param("selectLanguageList") List<Integer> selectLanguageList);//선택한 요건에 맞는 프로젝트 조회
}
