package kr.ac.smu.cs.comnet.dto;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.BoardVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public class BoardDTO {
	private BoardVO boardVO;
	private List<FieldVO> boardField;
	private List<LanguageVO> boardLanguage;
	private List<UserDTO> volunteerList;
	private List<UserDTO> partnerList;
	private boolean applied;
	private boolean partner;
	
	public BoardDTO(BoardVO boardVO, List<FieldVO> boardField, List<LanguageVO> boardLanguage) {
		this.boardVO = boardVO;
		this.boardField = boardField;
		this.boardLanguage = boardLanguage;
	}

	public BoardDTO(BoardVO boardVO, List<FieldVO> boardField, 
			List<LanguageVO> boardLanguage, List<UserDTO> volunteerList, List<UserDTO> partnerList,boolean applied,boolean partner) {
		this.boardVO=boardVO;
		this.boardField=boardField;
		this.boardLanguage=boardLanguage;
		this.volunteerList=volunteerList;
		this.partnerList=partnerList;
		this.applied=applied;
		this.partner=partner;
	}
	
	public boolean isPartner() {
		return partner;
	}

	public void setPartner(boolean partner) {
		this.partner = partner;
	}

	public List<UserDTO> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<UserDTO> partnerList) {
		this.partnerList = partnerList;
	}

	public List<UserDTO> getVolunteerList() {
		return volunteerList;
	}

	public void setVolunteerList(List<UserDTO> volunteerList) {
		this.volunteerList = volunteerList;
	}

	public boolean isApplied() {
		return applied;
	}

	public void setApplied(boolean applied) {
		this.applied = applied;
	}

	public BoardVO getBoardVO() {
		return boardVO;
	}
	public void setBoardVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}
	public List<FieldVO> getBoardField() {
		return boardField;
	}
	public void setBoardField(List<FieldVO> boardField) {
		this.boardField = boardField;
	}
	public List<LanguageVO> getBoardLanguage() {
		return boardLanguage;
	}
	public void setBoardLanguage(List<LanguageVO> boardLanguage) {
		this.boardLanguage = boardLanguage;
	}
}
