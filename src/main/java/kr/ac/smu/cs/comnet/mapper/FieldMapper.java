package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldMapper {
	
	public List<FieldVO> selectList();//분야 전체 조회

	public void registerUserField(@Param("uid") int uid, @Param("fid") int fid);//user_field 등록
	
	public void registerBoardField(@Param("bid") int bid, @Param("fid") int fid);//board_field 등록
	
	public List<Conn_bfVO> selectConn_bfList();//DB접속 시간을 줄이기 위해 한번에  bid로 오름차순 정렬해 반환
	
	@Cacheable("FieldCache")
	public FieldVO select(int fid);//영역 반환
	
	public List<Conn_bfVO> selectSuitableConn_bfList(@Param("bidList") List<Integer> bidList);//적합한 conn_bf반환
	
	public List<Conn_bfVO> selectConn_bf(int bid);//프로젝트 하나의 conn_bf 반환
}
