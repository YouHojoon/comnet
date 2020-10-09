package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;


public interface BoardService {
	
	public List<BoardDTO> selectList();//������Ʈ ��ü ��ȸ
	
	public void register(BoardVO boardVO, int[] boardField, int[] boardLanguage);//������Ʈ ���
	
	public List<BoardDTO> selectSuitableBoardList(int[] fieldList, int[] languageList);//���ϴ� ����� ������Ʈ ��ȸ
	
	public BoardDTO select(int bid, String email);//������Ʈ �� ��ȸ
	
	public BoardVO select(int bid);//������Ʈ �� ��ȸ
	
	public void update(BoardVO boardVO, List<Integer> boardField, List<Integer> boardLanguage);//������Ʈ ����

	public void delete(int bid);//������Ʈ ����
	
	public List<BoardDTO> selectMyProjectList(int uid);//���� ������Ʈ ��� ��ȸ
	
	public void applyToProject(int bid, int vid);//������Ʈ ����
	
	public BoardDTO selectMyProject(int bid);//���� ������Ʈ ��ȸ
	
	public void applyCancel(int bid, String email);//������Ʈ ���� ���
	
	public void applyCancelByVid(int bid, int vid);//������Ʈ ���� ���
	
	public void agree(int bid, int vid);//������Ʈ ���� ����
	
	public List<BoardDTO> selectVolunteerProjectList(int uid);//���� ������ ������Ʈ ��� ��ȸ
	
	public BoardDTO selectVolunteerProject(int bid);//���� ������ ������Ʈ ��ȸ
	
	public void eliminatePartner(int bid, int pid);//���� �߹�
}
