package kr.ac.smu.cs.comnet.dto;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.BoardVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

public class BoardDTO {
	private BoardVO boardVO;
	private List<FieldVO> board_field;
	private List<LanguageVO> board_language;
	public BoardDTO(BoardVO boardVO, List<FieldVO> board_field, 
			List<LanguageVO> board_language) {
		this.boardVO=boardVO;
		this.board_field=board_field;
		this.board_language=board_language;
	}
	public BoardVO getBoardVO() {
		return boardVO;
	}
	public void setBoardVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}
	public List<FieldVO> getBoard_field() {
		return board_field;
	}
	public void setBoard_field(List<FieldVO> board_field) {
		this.board_field = board_field;
	}
	public List<LanguageVO> getBoard_language() {
		return board_language;
	}
	public void setBoard_language(List<LanguageVO> board_language) {
		this.board_language = board_language;
	}
	
}
