package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldDAO {
	public List<FieldVO> selectList();//분야 전체 조회

	public void registerUserField(int uid, int fid);//user_field 등록
	
	public void registerBoardField(int bid, int fid);//board_field 등록
	
	public List<Conn_bfVO> selectConn_bfList();//DB접속 시간을 줄이기 위해 한번에  bid로 오름차순 정렬해 반환
	
	public FieldVO select(int fid);//영역 반환
}
