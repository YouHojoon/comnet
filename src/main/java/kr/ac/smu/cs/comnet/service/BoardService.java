package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.dto.BoardDTO;


public interface BoardService {
	
	public List<BoardDTO> selectList();//프로젝트 전체 조회
}
