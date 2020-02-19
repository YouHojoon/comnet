package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldMapper {
	public List<FieldVO> selectList();//분야 전체 조회
	
	public List<FieldVO> selectBoardField(int bid);//프로젝트 영역 조회
	
	public void registerUserField(@Param("uid") int uid, @Param("fid") int fid);//user_field 등록
}
