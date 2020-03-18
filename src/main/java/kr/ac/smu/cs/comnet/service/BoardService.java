package kr.ac.smu.cs.comnet.service;

import java.util.List;


import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;


public interface BoardService {
	
	public List<BoardDTO> selectList();//������Ʈ ��ü ��ȸ
	
	public void register(BoardVO boardVO, int[] boardField, int[] boardLanguage);//������Ʈ ���
	
	public List<BoardDTO> selectSuitableBoardList(int[] fieldList, int[] languageList);//���ϴ� ����� ������Ʈ ��ȸ
	
	public BoardDTO select(int bid);//������Ʈ �� ��ȸ
	
	public void update(BoardVO boardVO, int[] boardField, int[] boardLanguage);//������Ʈ ����

	public void delete(int bid);//������Ʈ ����
	
	public List<BoardDTO> selectMyProjectList(int uid);//���� ������Ʈ ��ȸ
}
