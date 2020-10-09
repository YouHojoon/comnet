package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.BoardVO;
import kr.ac.smu.cs.comnet.vo.Conn_bvVO;
import kr.ac.smu.cs.comnet.vo.Conn_ubVO;

public interface BoardMapper {
	
	public List<BoardVO> selectList();//������Ʈ ��ü ��ȸ
	
	public void register(BoardVO boardVO);//������Ʈ ���
	
	public int selectBid(String reg_date);//boardField, boardLangueage ����Ҷ� bid�� �ʿ��ؼ�
	
	public List<BoardVO> selectSuitableBoardList(@Param("selectFieldList") int[] selectFieldList, 
			@Param("selectLanguageList") int[] selectLanguageList);//������ ��ǿ� �´� ������Ʈ ��ȸ
	
	@Cacheable("BoardCache")//������Ʈ �ۼ��� Ȯ���� �ϸ鼭 ��ȸ�� �� �ߺ� ��ȸ�� ���� ���� ĳ�ø� ���
	public BoardVO select(int bid);//������Ʈ �� ��ȸ
	
	@CacheEvict(cacheNames = "BoardCache" , allEntries = true)//������Ʈ ������ �ϸ� ĳ�� ����
	public void update(BoardVO boardVO);//������Ʈ ����
	
	public void delete(int bid);//������Ʈ ����
	
	public List<BoardVO> selectMyProjectList(int uid);//���� ������Ʈ ��ȸ

	public int[] selectMyProjectBidList(int uid);//���� ������Ʈ bid ��ȸ
	
	public void deleteMyProject(int uid);//���� ������Ʈ ��� ����
	
	public void deleteRelevant(@Param("bidList") int[] bidList);//���õ� ���� �ܷ�Ű ����
	
	public void applyToProject(@Param("bid") int bid, @Param("vid") int vid);//������Ʈ ����
	
	public List<Conn_bvVO> selectConn_bvList(int bid);//conn_bv ��� ��ȸ
	
	public void applyCancel(@Param("bid") int bid, @Param("vid") int vid);//������Ʈ ���� ���
	
	public void approval(@Param("bid") int bid, @Param("vid") int vid);//������Ʈ ���� ����
	
	public List<Conn_ubVO> selectConn_ubList(int bid);//conn_ub ��� ��ȸ
	
	public Conn_ubVO selectConn_ub(int bid, int uid);//conn_ub ��ȸ
	
	public Conn_bvVO selectConn_bv(int bid, int vid);//conn_bv ��ȸ
	
	public List<BoardVO> selectVolunteerProjectList(int uid);//���� ������ ������Ʈ ��ȸ
	
	public void eliminatePartner(@Param("bid") int bid, @Param("pid") int pid);//���� �߹�
}
