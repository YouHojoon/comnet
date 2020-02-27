package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageMapper {
	
	public List<LanguageVO> selectList();//��� ��ü ��ȸ
	
	public void regiserUserLanguage(@Param("uid") int uid,@Param("lid") int lid);//user_language ���
	
	public void regiserBoardLanguage(@Param("bid") int bid,@Param("lid") int lid);//board_language ���
	
	public List<Conn_blVO> selectConn_blList();//DB���� �ð��� ���̱� ���� �ѹ���  bid�� �������� ������ ��ȯ
	
	@Cacheable("LanguageCache")
	public LanguageVO select(int lid);//��� ��ȯ
	
}
