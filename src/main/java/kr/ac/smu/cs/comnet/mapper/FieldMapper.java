package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldMapper {
	public List<FieldVO> selectList();//�о� ��ü ��ȸ
	
	public List<FieldVO> selectBoardField(int bid);//������Ʈ ���� ��ȸ
}
