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
		if(boardList.size()==0)//프로젝트가 없으면 null 반환
			return null;
		List<BoardDTO> boardDTOList=new ArrayList<BoardDTO>();//O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		List<Conn_bfVO> conn_bfList=new ArrayList<Conn_bfVO>();
		List<Conn_blVO> conn_blList=new ArrayList<Conn_blVO>();
		conn_bfList=fMapper.selectConn_bfList();
		conn_blList=lMapper.selectConn_blList();
		int i=0, q=0;
		for(BoardVO boardVO : boardList) {
			int bid=boardVO.getBid();
			List<FieldVO> boardField=new ArrayList<FieldVO>();
			List<LanguageVO> boardLanguage=new ArrayList<LanguageVO>();
			for(; i<conn_bfList.size(); i++) {
				Conn_bfVO conn_bfVO= conn_bfList.get(i);
				if(conn_bfVO.getBid()==bid) 
					boardField.add(fMapper.select(conn_bfVO.getFid()));
				else
					break;//프로젝트 모집 분야가 끝나면 탈출
			}
			for(; q<conn_blList.size(); q++) {
				Conn_blVO conn_blVO= conn_blList.get(q);
				if(conn_blVO.getBid()==bid)
					boardLanguage.add(lMapper.select(conn_blVO.getLid()));
				else
					break;//프로젝트 모집 언어가 끝나면 탈출
			}
			boardDTOList.add(new BoardDTO(boardVO, boardField, boardLanguage));
		}
		long end=System.currentTimeMillis();
		log.info(Long.toString(end-start));
		return boardDTOList;
	}
	@Override
	public void register(BoardVO boardVO, int[] boardField, int[] boardLanguage) {
		boardVO.setReg_date(new Timestamp(System.currentTimeMillis()).toString());
		bMapper.register(boardVO);
		for(int fid : boardField)
			fMapper.registerBoardField(bMapper.selectBid(boardVO.getReg_date()), fid);
		for(int lid : boardLanguage)
			lMapper.registerBoardLanguage(bMapper.selectBid(boardVO.getReg_date()), lid);
	}
	@Override
	public List<BoardDTO> selectSuitableBoardList(List<Integer> fieldList, List<Integer> languageList) {
		long start=System.currentTimeMillis();
		List<BoardVO> boardList=bMapper.selectSuitableBoardList(fieldList, languageList);
		if(boardList.size()==0)//프로젝트가 없으면 null 반환
			return null;
		List<Integer> bidList=new ArrayList<Integer>();
		List<BoardDTO> boardDTOList=new ArrayList<BoardDTO>();//O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		List<Conn_bfVO> conn_bfList=new ArrayList<Conn_bfVO>();
		List<Conn_blVO> conn_blList=new ArrayList<Conn_blVO>();
		int i=0, q=0, rowNum=1;
		for(BoardVO boardVO : boardList) { 
			bidList.add(boardVO.getBid());
			boardVO.setRowNum(rowNum);
			rowNum++;
		}	
		conn_bfList=fMapper.selectSuitableConn_bfList(bidList);
		conn_blList=lMapper.selectSuitableConn_blList(bidList);
		for(BoardVO boardVO : boardList) {
			int bid=boardVO.getBid();
			List<FieldVO> board_field=new ArrayList<FieldVO>();
			List<LanguageVO> board_language=new ArrayList<LanguageVO>();
			for(; i<conn_bfList.size(); i++) {
				Conn_bfVO conn_bfVO= conn_bfList.get(i);
				if(conn_bfVO.getBid()==bid) 
					board_field.add(fMapper.select(conn_bfVO.getFid()));
				else
					break;//프로젝트 모집 분야가 끝나면 탈출
			}
			for(; q<conn_blList.size(); q++) {
				Conn_blVO conn_blVO= conn_blList.get(q);
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
	public BoardDTO select(int bid) {
		BoardVO board = bMapper.select(bid);
		List<Conn_bfVO> conn_bfList = fMapper.selectBoardConn_bfList(board.getBid());
		List<Conn_blVO> conn_blList = lMapper.selectBoardConn_blList(board.getBid());
		List<FieldVO> boardField=new ArrayList<FieldVO>();
		List<LanguageVO> boardLanguage=new ArrayList<LanguageVO>();
		for(Conn_bfVO conn_bfVO : conn_bfList) {
			if(conn_bfVO.getBid()==bid) 
				boardField.add(fMapper.select(conn_bfVO.getFid()));
			else
				break;//프로젝트 모집 분야가 끝나면 탈출
		}
		for(Conn_blVO conn_blVO : conn_blList) {
			if(conn_blVO.getBid()==bid)
				boardLanguage.add(lMapper.select(conn_blVO.getLid()));
			else
				break;//프로젝트 모집 언어가 끝나면 탈출
		}
		return new BoardDTO(board, boardField, boardLanguage);
	}
	@Override
	public void update(BoardVO boardVO, List<Integer> boardField, List<Integer> boardLanguage) {
		bMapper.update(boardVO);
		int bid = boardVO.getBid();
		fMapper.deleteConn_bf(bid);
		lMapper.deleteConn_bl(bid);
		for(int fid : boardField)
			fMapper.registerBoardField(bid,fid);
		for(int lid : boardLanguage)
			lMapper.registerBoardLanguage(bid,lid);	
		}
	@Override
	public void delete(int bid) {
		fMapper.deleteConn_bf(bid);
		lMapper.deleteConn_bl(bid);
		bMapper.delete(bid);
	}
	@Override
	public List<BoardDTO> selectMyProjectList(int uid) {
		long start=System.currentTimeMillis();
		List<BoardVO> boardList=bMapper.selectMyProjectList(uid);
		if(boardList.size()==0)//프로젝트가 없으면 null 반환
			return null;
		List<Integer> bidList=new ArrayList<Integer>();
		List<BoardDTO> boardDTOList=new ArrayList<BoardDTO>();//O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		List<Conn_bfVO> conn_bfList=new ArrayList<Conn_bfVO>();
		List<Conn_blVO> conn_blList=new ArrayList<Conn_blVO>();
		int i=0, q=0, rowNum=1;
		for(BoardVO boardVO : boardList) { 
			bidList.add(boardVO.getBid());
			boardVO.setRowNum(rowNum);
			rowNum++;
		}	
		conn_bfList=fMapper.selectSuitableConn_bfList(bidList);
		conn_blList=lMapper.selectSuitableConn_blList(bidList);
		for(BoardVO boardVO : boardList) {
			int bid=boardVO.getBid();
			List<FieldVO> board_field=new ArrayList<FieldVO>();
			List<LanguageVO> board_language=new ArrayList<LanguageVO>();
			for(; i<conn_bfList.size(); i++) {
				Conn_bfVO conn_bfVO= conn_bfList.get(i);
				if(conn_bfVO.getBid()==bid) 
					board_field.add(fMapper.select(conn_bfVO.getFid()));
				else
					break;//프로젝트 모집 분야가 끝나면 탈출
			}
			for(; q<conn_blList.size(); q++) {
				Conn_blVO conn_blVO= conn_blList.get(q);
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
}
