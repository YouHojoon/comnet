package kr.ac.smu.cs.comnet.service;

import java.util.List;


import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;


public interface BoardService {
	
	public List<BoardDTO> selectList();//프로젝트 전체 조회
	
	public void register(BoardVO boardVO, int[] board_field, int[] board_language);//프로젝트 등록
	
	public List<BoardDTO> selectSuitableList(List<Integer> fieldList, List<Integer> languageList);//원하는 요건의 프로젝트 조회
}
