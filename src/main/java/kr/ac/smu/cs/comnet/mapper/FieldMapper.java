package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldMapper {
	
	public List<FieldVO> selectList();//�о� ��ü ��ȸ

	public void registerUserField(@Param("uid") int uid, @Param("fid") int fid);//user_field ���
	
	public void registerBoardField(@Param("bid") int bid, @Param("fid") int fid);//board_field ���
	
	public List<Conn_bfVO> selectConn_bfList();//DB���� �ð��� ���̱� ���� �ѹ���  bid�� �������� ������ ��ȯ
	
	@Cacheable("FieldCache")
	public FieldVO select(int fid);//���� ��ȯ
	
	public List<Conn_bfVO> selectSuitableConn_bfList(@Param("bidList") List<Integer> bidList);//������ conn_bf��ȯ
	
	public List<Conn_bfVO> selectConn_bf(int bid);//������Ʈ �ϳ��� conn_bf ��ȯ
}
