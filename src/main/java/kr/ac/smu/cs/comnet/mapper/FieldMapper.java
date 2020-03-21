package kr.ac.smu.cs.comnet.mapper;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.Conn_ufVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;

public interface FieldMapper {
	
	public List<FieldVO> selectList();//�о� ��ü ��ȸ

	public void registerUserField(@Param("uid") int uid, @Param("userField") int[] userField);//userField ���
	
	public void registerBoardField(@Param("bid") int bid, @Param("boardField") int[] boardField);//boardField ���
	
	public LinkedList<Conn_bfVO> selectConn_bfList();//DB���� �ð��� ���̱� ���� �ѹ���  bid�� �������� ������ ��ȯ
	
	@Cacheable("FieldCache")
	public FieldVO select(int fid);//���� ��ȯ
	
	public LinkedList<Conn_bfVO> selectSuitableConn_bfList(@Param("bidList") List<Integer> bidList);//������ conn_bf��ȯ
	
	public LinkedList<Conn_bfVO> selectBoardConn_bfList(int bid);//������Ʈ �ϳ��� conn_bf ��ȯ
	
	public void deleteConn_bf(int bid);//������Ʈ�� conn_bf ����
	
	public List<Conn_ufVO> selectUserField(int uid);//������  conn_uf ��ȯ
	
	public void deleteConn_uf(int uid);//������ conn_uf ����
	
	public void updateBoardField(@Param("bid") int bid, @Param("boardField") List<Integer> boardField);//������Ʈ ���� �о� ������Ʈ
	
	public void updateUserField(@Param("uid") int uid, @Param("userField") List<Integer> userField);//���� ���� �о� ������Ʈ

	public void deleteConn_bfByBidList(@Param("bidList") int[] bidList);//���� ������Ʈ�� conn_bf ����
}
