package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> selectList();//������Ʈ ��ü ��ȸ
	
	public void register(BoardVO boardVO);//������Ʈ ���
	
	public int select(String reg_date);//board_field, board_langueage ����Ҷ� bid�� �ʿ��ؼ�
}
