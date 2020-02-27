package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public interface LanguageMapper {
	
	public List<LanguageVO> selectList();//언어 전체 조회
	
	public void regiserUserLanguage(@Param("uid") int uid,@Param("lid") int lid);//user_language 등록
	
	public void regiserBoardLanguage(@Param("bid") int bid,@Param("lid") int lid);//board_language 등록
	
	public List<Conn_blVO> selectConn_blList();//DB접속 시간을 줄이기 위해 한번에  bid로 오름차순 정렬해 반환
	
	@Cacheable("LanguageCache")
	public LanguageVO select(int lid);//언어 반환
	
}
