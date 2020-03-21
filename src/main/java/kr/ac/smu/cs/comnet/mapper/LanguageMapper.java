package kr.ac.smu.cs.comnet.mapper;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.Conn_ulVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageMapper {
	
	public List<LanguageVO> selectList();//��� ��ü ��ȸ
	
	public void registerUserLanguage(@Param("uid") int uid,@Param("userLanguage") int[] userLanguage);//userLanguage ���
	
	public void registerBoardLanguage(@Param("bid") int bid,@Param("boardLanguage") int[] boardLanguage);//boardLanguage ���
	
	public LinkedList<Conn_blVO> selectConn_blList();//DB���� �ð��� ���̱� ���� �ѹ���  bid�� �������� ������ ��ȯ
	
	@Cacheable("LanguageCache")
	public LanguageVO select(int lid);//��� ��ȯ
	
	public LinkedList<Conn_blVO> selectSuitableConn_blList(@Param("bidList") List<Integer> bidList);//������ conn_bl��ȯ
	
	public LinkedList<Conn_blVO> selectBoardConn_blList(int bid);//������Ʈ �ϳ��� conn_bl ��ȯ
	
	public void deleteConn_bl(int bid);//������Ʈ�� conn_bl ����
	
	public List<Conn_ulVO> selectUserLanguage(int uid);//������ conn_ul ��ȯ
	
	public void deleteConn_ul(int uid);//������ conn_ul ����
	
	public void updateBoardLanguage(@Param("bid") int bid, @Param("boardLanguage") List<Integer> boardLanguage);
	
	public void updateUserLanguage(@Param("uid") int uid, @Param("userLanguage") List<Integer> userLanguage);//���� ���� �о� ������Ʈ
	
	public void deleteConn_blByBidList(@Param("bidList") int[] bidList);//���� ������Ʈ�� conn_bl ����
}
