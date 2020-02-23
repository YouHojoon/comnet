package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> selectList();//프로젝트 전체 조회
	
	public void register(BoardVO boardVO);//프로젝트 등록
	
	public int select(String reg_date);//board_field, board_langueage 등록할때 bid가 필요해서
}
