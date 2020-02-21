package kr.ac.smu.cs.comnet.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.dao.BoardDAO;
import kr.ac.smu.cs.comnet.dao.FieldDAO;
import kr.ac.smu.cs.comnet.dao.LanguageDAO;
import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;
import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO bDAO;
	@Autowired
	private FieldDAO fDAO;
	@Autowired
	private LanguageDAO lDAO;
	private Logger log=LoggerFactory.getLogger(BoardServiceImpl.class);
	@Override
	public List<BoardDTO> selectList() {
		long start=System.currentTimeMillis();
		List<BoardVO> boardList=bDAO.selectList();
		List<BoardDTO> boardDTOList=new ArrayList<BoardDTO>();//O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		List<Conn_bfVO> field_list=new ArrayList<Conn_bfVO>();
		List<Conn_blVO> language_list=new ArrayList<Conn_blVO>();
		field_list=fDAO.selectConn_bfList();
		language_list=lDAO.selectConn_blList();
		int i=0, q=0;
		for(BoardVO boardVO : boardList) {
			int bid=boardVO.getBid();
			List<FieldVO> board_field=new ArrayList<FieldVO>();
			List<LanguageVO> board_language=new ArrayList<LanguageVO>();
			for(; i<field_list.size(); i++) {
				Conn_bfVO conn_bfVO= field_list.get(i);
				if(conn_bfVO.getBid()==bid) 
					board_field.add(fDAO.select(conn_bfVO.getFid()));
				else
					break;//프로젝트 모집 분야가 끝나면 탈출
			}
			for(; q<language_list.size(); q++) {
				Conn_blVO conn_blVO= language_list.get(q);
				if(conn_blVO.getBid()==bid)
					board_language.add(lDAO.select(conn_blVO.getLid()));
				else
					break;//프로젝트 모집 언어가 끝나면 탈출
			}
			boardDTOList.add(new BoardDTO(boardVO, board_field, board_language));
		}
		long end=System.currentTimeMillis();
		log.info(Long.toString(end-start));
		return boardDTOList;
	}
	@Override
	public void register(BoardVO boardVO, int[] board_field, int[] board_language) {
		boardVO.setReg_date(new Timestamp(System.currentTimeMillis()).toString());
		bDAO.register(boardVO);
		for(int fid : board_field)
			fDAO.registerBoardField(bDAO.select(boardVO.getReg_date()), fid);
		for(int lid : board_language)
			lDAO.regiserBoardLanguage(bDAO.select(boardVO.getReg_date()), lid);
	}
}
