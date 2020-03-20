package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.Conn_ufVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;


public interface FieldService {
	
	public List<FieldVO> selectList();//분야 전체 조회
	
	public List<Conn_ufVO> selectUserField(int uid);//유저의  conn_uf 반환
	
}
