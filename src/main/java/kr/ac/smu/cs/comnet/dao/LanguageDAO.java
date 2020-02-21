package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageDAO {
	public List<LanguageVO> selectList();//��� ��ü ��ȸ
	
	public void regiserUserLanguage(int uid, int lid);//user_language ���
	
	public void regiserBoardLanguage(int bid, int lid);//board_language ���
	
	public List<Conn_blVO> selectConn_blList();//DB���� �ð��� ���̱� ���� �ѹ���  bid�� �������� ������ ��ȯ
	
	public LanguageVO select(int lid);//��� ��ȯ
}
