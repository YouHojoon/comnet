package kr.ac.smu.cs.comnet.service;

import java.util.ArrayList;
import java.util.List;


import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;


public interface BoardService {
	
	public List<BoardDTO> selectList();//������Ʈ ��ü ��ȸ
	
	public void register(BoardVO boardVO, int[] boardField, int[] boardLanguage);//������Ʈ ���
	
	public List<BoardDTO> selectSuitableList(List<Integer> fieldList, List<Integer> languageList);//���ϴ� ����� ������Ʈ ��ȸ
	
	public BoardDTO select(int bid);//������Ʈ �� ��ȸ
	
	public void update(BoardVO boardVO, List<Integer> boardField, List<Integer> boardLanguage);//������Ʈ ����
}
