package kr.ac.smu.cs.comnet.mapper;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.Conn_ufVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldMapper {
	
	public List<FieldVO> selectList();//분야 전체 조회

	public void registerUserField(@Param("uid") int uid, @Param("userField") int[] userField);//userField 등록
	
	public void registerBoardField(@Param("bid") int bid, @Param("boardField") int[] boardField);//boardField 등록
	
	public LinkedList<Conn_bfVO> selectConn_bfList();//DB접속 시간을 줄이기 위해 한번에  bid로 오름차순 정렬해 반환
	
	@Cacheable("FieldCache")
	public FieldVO select(int fid);//영역 반환
	
	public LinkedList<Conn_bfVO> selectSuitableConn_bfList(@Param("bidList") List<Integer> bidList);//적합한 conn_bf반환
	
	public LinkedList<Conn_bfVO> selectBoardConn_bfList(int bid);//프로젝트 하나의 conn_bf 반환
	
	public void deleteConn_bf(int bid);//프로젝트의 conn_bf 제거
	
	public List<Conn_ufVO> selectUserField(int uid);//유저의  conn_uf 반환
	
	public void deleteConn_uf(int uid);//유저의 conn_uf 제거
	
	public void updateBoardField(@Param("bid") int bid, @Param("boardField") List<Integer> boardField);//프로젝트 모집 분야 업데이트
	
	public void updateUserField(@Param("uid") int uid, @Param("userField") List<Integer> userField);//유저 관심 분야 업데이트

	public void deleteConn_bfByBidList(@Param("bidList") int[] bidList);//여러 프로젝트의 conn_bf 제거
}
