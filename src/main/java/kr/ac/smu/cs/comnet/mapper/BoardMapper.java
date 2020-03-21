package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> selectList();//프로젝트 전체 조회
	
	public void register(BoardVO boardVO);//프로젝트 등록
	
	public int selectBid(String reg_date);//boardField, boardLangueage 등록할때 bid가 필요해서
	
	public List<BoardVO> selectSuitableBoardList(@Param("selectFieldList") int[] selectFieldList, 
			@Param("selectLanguageList") int[] selectLanguageList);//선택한 요건에 맞는 프로젝트 조회
	
	@Cacheable("BoardCache")//프로젝트 작성자 확인을 하면서 조회를 해 중복 조회를 막기 위해 캐시를 사용
	public BoardVO select(int bid);//프로젝트 상세 조회
	
	@CacheEvict(cacheNames = "BoardCache" , allEntries = true)//프로젝트 수정을 하면 캐시 삭제
	public void update(BoardVO boardVO);//프로젝트 수정
	
	public void delete(int bid);//프로젝트 삭제
	
	public List<BoardVO> selectMyProjectList(int uid);//나의 프로젝트 조회
}
