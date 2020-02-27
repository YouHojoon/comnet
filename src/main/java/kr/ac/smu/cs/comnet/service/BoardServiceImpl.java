package kr.ac.smu.cs.comnet.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.mapper.BoardMapper;
import kr.ac.smu.cs.comnet.mapper.FieldMapper;
import kr.ac.smu.cs.comnet.mapper.LanguageMapper;
import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;
import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper bMapper;
	@Autowired
	private FieldMapper fMapper;
	@Autowired
	private LanguageMapper lMapper;
	private Logger log=LoggerFactory.getLogger(BoardServiceImpl.class);
	@Override
	public List<BoardDTO> selectList() {
		long start=System.currentTimeMillis();
		List<BoardVO> boardList=bMapper.selectList();
		List<BoardDTO> boardDTOList=new ArrayList<BoardDTO>();//O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		List<Conn_bfVO> fieldList=new ArrayList<Conn_bfVO>();
		List<Conn_blVO> languageList=new ArrayList<Conn_blVO>();
		fieldList=fMapper.selectConn_bfList();
		languageList=lMapper.selectConn_blList();
		int i=0, q=0;
		for(BoardVO boardVO : boardList) {
			int bid=boardVO.getBid();
			List<FieldVO> board_field=new ArrayList<FieldVO>();
			List<LanguageVO> board_language=new ArrayList<LanguageVO>();
			for(; i<fieldList.size(); i++) {
				Conn_bfVO conn_bfVO= fieldList.get(i);
				if(conn_bfVO.getBid()==bid) 
					board_field.add(fMapper.select(conn_bfVO.getFid()));
				else
					break;//프로젝트 모집 분야가 끝나면 탈출
			}
			for(; q<languageList.size(); q++) {
				Conn_blVO conn_blVO= languageList.get(q);
				if(conn_blVO.getBid()==bid)
					board_language.add(lMapper.select(conn_blVO.getLid()));
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
		bMapper.register(boardVO);
		for(int fid : board_field)
			fMapper.registerBoardField(bMapper.select(boardVO.getReg_date()), fid);
		for(int lid : board_language)
			lMapper.regiserBoardLanguage(bMapper.select(boardVO.getReg_date()), lid);
	}
}
