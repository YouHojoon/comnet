package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> selectList();//프로젝트 전체 조회
}
