package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> selectList();//������Ʈ ��ü ��ȸ
	
	public void register(BoardVO boardVO);//������Ʈ ���
	
	public int selectBid(String reg_date);//board_field, board_langueage ����Ҷ� bid�� �ʿ��ؼ�
	
	public List<BoardVO> selectSuitableBoard(@Param("selectFieldList") List<Integer> selectFieldList, 
			@Param("selectLanguageList") List<Integer> selectLanguageList);//������ ��ǿ� �´� ������Ʈ ��ȸ
	
	@Cacheable("BoardCache")//������Ʈ �ۼ��� Ȯ���� �ϸ鼭 ��ȸ�� �� �ߺ� ��ȸ�� ���� ���� ĳ�ø� ���
	public BoardVO select(int bid);//������Ʈ �� ��ȸ
	
	@CacheEvict(cacheNames = "BoardCache" , allEntries = true)//������Ʈ ������ �ϸ� ĳ�� ����
	public void update(BoardVO boardVO);//������Ʈ ����
	
	public void delete(int bid);//������Ʈ ����
	
	public List<BoardVO> selectMyProject(int uid);//���� ������Ʈ ��ȸ
}
