package kr.ac.smu.cs.comnet.mapper;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.Conn_ulVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageMapper {
	
	public List<LanguageVO> selectList();//언어 전체 조회
	
	public void registerUserLanguage(@Param("uid") int uid,@Param("userLanguage") int[] userLanguage);//userLanguage 등록
	
	public void registerBoardLanguage(@Param("bid") int bid,@Param("boardLanguage") int[] boardLanguage);//boardLanguage 등록
	
	public LinkedList<Conn_blVO> selectConn_blList();//DB접속 시간을 줄이기 위해 한번에  bid로 오름차순 정렬해 반환
	
	@Cacheable("LanguageCache")
	public LanguageVO select(int lid);//언어 반환
	
	public LinkedList<Conn_blVO> selectSuitableConn_blList(@Param("bidList") List<Integer> bidList);//적합한 conn_bl반환
	
	public LinkedList<Conn_blVO> selectBoardConn_blList(int bid);//프로젝트 하나의 conn_bl 반환
	
	public void deleteConn_bl(int bid);//프로젝트의 conn_bl 제거
	
	public List<Conn_ulVO> selectUserLanguage(int uid);//유저의 conn_ul 반환
	
	public void deleteConn_ul(int uid);//유저의 conn_ul 제거
	
	public void updateBoardLanguage(@Param("bid") int bid, @Param("boardLanguage") List<Integer> boardLanguage);
	
	public void updateUserLanguage(@Param("uid") int uid, @Param("userLanguage") List<Integer> userLanguage);//유저 관심 분야 업데이트
	
	public void deleteConn_blByBidList(@Param("bidList") int[] bidList);//여러 프로젝트의 conn_bl 제거
}
