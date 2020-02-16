package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> selectList();//프로젝트 전체 조회
	
}
