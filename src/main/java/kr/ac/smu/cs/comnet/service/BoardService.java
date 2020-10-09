package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;


public interface BoardService {
	
	public List<BoardDTO> selectList();//프로젝트 전체 조회
	
	public void register(BoardVO boardVO, int[] boardField, int[] boardLanguage);//프로젝트 등록
	
	public List<BoardDTO> selectSuitableBoardList(int[] fieldList, int[] languageList);//원하는 요건의 프로젝트 조회
	
	public BoardDTO select(int bid, String email);//프로젝트 상세 조회
	
	public BoardVO select(int bid);//프로젝트 상세 조회
	
	public void update(BoardVO boardVO, List<Integer> boardField, List<Integer> boardLanguage);//프로젝트 수정

	public void delete(int bid);//프로젝트 삭제
	
	public List<BoardDTO> selectMyProjectList(int uid);//나의 프로젝트 목록 조회
	
	public void applyToProject(int bid, int vid);//프로젝트 지원
	
	public BoardDTO selectMyProject(int bid);//나의 프로젝트 조회
	
	public void applyCancel(int bid, String email);//프로젝트 지원 취소
	
	public void applyCancelByVid(int bid, int vid);//프로젝트 지원 취소
	
	public void agree(int bid, int vid);//프로젝트 지원 승인
	
	public List<BoardDTO> selectVolunteerProjectList(int uid);//내가 지원한 프로젝트 목록 조회
	
	public BoardDTO selectVolunteerProject(int bid);//내가 지원한 프로젝트 조회
	
	public void eliminatePartner(int bid, int pid);//팀원 추방
}
