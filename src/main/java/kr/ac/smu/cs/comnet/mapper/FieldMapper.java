package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldMapper {
	public List<FieldVO> selectList();//�о� ��ü ��ȸ
	
	public List<FieldVO> selectBoardField(int bid);//������Ʈ ���� ��ȸ
	
	public void registerUserField(@Param("uid") int uid, @Param("fid") int fid);//user_field ���
}
