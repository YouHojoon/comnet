package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.Conn_ufVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;


public interface FieldService {
	
	public List<FieldVO> selectList();//�о� ��ü ��ȸ
	
	public List<Conn_ufVO> selectUserField(int uid);//������  conn_uf ��ȯ
	
}
