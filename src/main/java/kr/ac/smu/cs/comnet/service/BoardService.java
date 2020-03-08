package kr.ac.smu.cs.comnet.service;

import java.util.ArrayList;
import java.util.List;


import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;


public interface BoardService {
	
	public List<BoardDTO> selectList();//프로젝트 전체 조회
	
	public void register(BoardVO boardVO, int[] boardField, int[] boardLanguage);//프로젝트 등록
	
	public List<BoardDTO> selectSuitableList(List<Integer> fieldList, List<Integer> languageList);//원하는 요건의 프로젝트 조회
	
	public BoardDTO select(int bid);//프로젝트 상세 조회
	
	public void update(BoardVO boardVO, List<Integer> boardField, List<Integer> boardLanguage);//프로젝트 수정
}
