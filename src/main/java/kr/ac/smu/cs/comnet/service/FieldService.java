package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.FieldVO;


public interface FieldService {
	
	public List<FieldVO> selectList();//분야 전체 조회
	
	public List<FieldVO> selectBoardField(int bid);//프로젝트 모집 분야 조회
}
