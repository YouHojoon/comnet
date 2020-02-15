package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldMapper {
	public List<FieldVO> selectList();//분야 전체 조회
	
	public List<FieldVO> selectBoardField(int bid);//프로젝트 영역 조회
}
