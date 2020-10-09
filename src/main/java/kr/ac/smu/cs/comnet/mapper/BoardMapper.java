package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.BoardVO;
import kr.ac.smu.cs.comnet.vo.Conn_bvVO;
import kr.ac.smu.cs.comnet.vo.Conn_ubVO;

public interface BoardMapper {
	
	public List<BoardVO> selectList();//프로젝트 전체 조회
	
	public void register(BoardVO boardVO);//프로젝트 등록
	
	public int selectBid(String reg_date);//boardField, boardLangueage 등록할때 bid가 필요해서
	
	public List<BoardVO> selectSuitableBoardList(@Param("selectFieldList") int[] selectFieldList, 
			@Param("selectLanguageList") int[] selectLanguageList);//선택한 요건에 맞는 프로젝트 조회
	
	@Cacheable("BoardCache")//프로젝트 작성자 확인을 하면서 조회를 해 중복 조회를 막기 위해 캐시를 사용
	public BoardVO select(int bid);//프로젝트 상세 조회
	
	@CacheEvict(cacheNames = "BoardCache" , allEntries = true)//프로젝트 수정을 하면 캐시 삭제
	public void update(BoardVO boardVO);//프로젝트 수정
	
	public void delete(int bid);//프로젝트 삭제
	
	public List<BoardVO> selectMyProjectList(int uid);//나의 프로젝트 조회

	public int[] selectMyProjectBidList(int uid);//나의 프로젝트 bid 조회
	
	public void deleteMyProject(int uid);//나의 프로젝트 모두 삭제
	
	public void deleteRelevant(@Param("bidList") int[] bidList);//관련된 유저 외래키 삭제
	
	public void applyToProject(@Param("bid") int bid, @Param("vid") int vid);//프로젝트 지원
	
	public List<Conn_bvVO> selectConn_bvList(int bid);//conn_bv 목록 조회
	
	public void applyCancel(@Param("bid") int bid, @Param("vid") int vid);//프로젝트 지원 취소
	
	public void approval(@Param("bid") int bid, @Param("vid") int vid);//프로젝트 지원 승인
	
	public List<Conn_ubVO> selectConn_ubList(int bid);//conn_ub 목록 조회
	
	public Conn_ubVO selectConn_ub(int bid, int uid);//conn_ub 조회
	
	public Conn_bvVO selectConn_bv(int bid, int vid);//conn_bv 조회
	
	public List<BoardVO> selectVolunteerProjectList(int uid);//내가 지원한 프로젝트 조회
	
	public void eliminatePartner(@Param("bid") int bid, @Param("pid") int pid);//팀원 추방
}
