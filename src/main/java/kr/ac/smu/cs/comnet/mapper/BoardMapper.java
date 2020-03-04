package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> selectList();//������Ʈ ��ü ��ȸ
	
	public void register(BoardVO boardVO);//������Ʈ ���
	
	public int select(String reg_date);//board_field, board_langueage ����Ҷ� bid�� �ʿ��ؼ�
	
	public List<BoardVO> selectSuitableList(@Param("selectFieldList") List<Integer> selectFieldList, 
			@Param("selectLanguageList") List<Integer> selectLanguageList);//������ ��ǿ� �´� ������Ʈ ��ȸ
}
