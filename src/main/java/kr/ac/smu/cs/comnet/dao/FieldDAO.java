package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldDAO {
	public List<FieldVO> selectList();//�о� ��ü ��ȸ

	public void registerUserField(int uid, int fid);//user_field ���
	
	public void registerBoardField(int bid, int fid);//board_field ���
	
	public List<Conn_bfVO> selectConn_bfList();//DB���� �ð��� ���̱� ���� �ѹ���  bid�� �������� ������ ��ȯ
	
	public FieldVO select(int fid);//���� ��ȯ
}
