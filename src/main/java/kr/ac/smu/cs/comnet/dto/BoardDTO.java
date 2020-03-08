package kr.ac.smu.cs.comnet.dto;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.BoardVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public class BoardDTO {
	private BoardVO boardVO;
	private List<FieldVO> boardField;
	private List<LanguageVO> boardLanguage;
	public BoardDTO(BoardVO boardVO, List<FieldVO> boardField, 
			List<LanguageVO> boardLanguage) {
		this.boardVO=boardVO;
		this.boardField=boardField;
		this.boardLanguage=boardLanguage;
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
