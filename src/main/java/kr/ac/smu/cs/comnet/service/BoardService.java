package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;


public interface BoardService {
	
	public List<BoardDTO> selectList();//������Ʈ ��ü ��ȸ
	
	public void register(BoardVO boardVO, int[] board_field, int[] board_language);//������Ʈ ���
}
