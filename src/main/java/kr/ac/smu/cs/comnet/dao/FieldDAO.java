package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldDAO {
	public List<FieldVO> selectList();//�о� ��ü ��ȸ
	
	public List<FieldVO> selectBoardField(int bid);//������Ʈ ���� �о� ��ȸ;
	
	public void registerUserField(int uid, int fid);//user_field ���
}
