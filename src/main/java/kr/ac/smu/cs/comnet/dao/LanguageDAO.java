package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageDAO {
	public List<LanguageVO> selectList();//��� ��ü ��ȸ
	
	public List<LanguageVO> selectBoardLanguage(int bid);//������Ʈ ���� ��� ��ȸ
	
	public void regiserUserLanguage(int uid, int lid);//user_language ���
}
