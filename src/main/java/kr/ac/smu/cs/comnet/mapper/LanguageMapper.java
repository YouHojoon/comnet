package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageMapper {
	
	public List<LanguageVO> selectList();//��� ��ü ��ȸ
	
	public List<LanguageVO> selectBoardLanguage(int bid);//������Ʈ ��� ��ȸ
}
