package kr.ac.smu.cs.comnet.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper bMapper;
	@Autowired
	private FieldMapper fMapper;
	@Autowired
	private LanguageMapper lMapper;
	private Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Override
	public List<BoardDTO> selectList() {
		long start = System.currentTimeMillis();
		List<BoardVO> boardList = bMapper.selectList();
		if (boardList.size() == 0)// 프로젝트가 없으면 null 반환
			return null;
		List<BoardDTO> boardDTOList = new ArrayList<BoardDTO>();// O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		Queue<Conn_bfVO> conn_bfList = fMapper.selectConn_bfList();
		Queue<Conn_blVO> conn_blList = lMapper.selectConn_blList();
		for (BoardVO boardVO : boardList) {
			int bid = boardVO.getBid();
			List<FieldVO> boardField = new ArrayList<FieldVO>();
			List<LanguageVO> boardLanguage = new ArrayList<LanguageVO>();
			while (!conn_bfList.isEmpty()) {
				Conn_bfVO conn_bfVO = conn_bfList.peek();
				if (conn_bfVO.getBid() == bid) {
					boardField.add(fMapper.select(conn_bfVO.getFid()));
					conn_bfList.remove();
				} else
					break;// 프로젝트 모집 분야가 끝나면 탈출
			}
			while (!conn_blList.isEmpty()) {
				Conn_blVO conn_blVO = conn_blList.peek();
				if (conn_blVO.getBid() == bid) {
					boardLanguage.add(lMapper.select(conn_blVO.getLid()));
					conn_blList.remove();
				} else
					break;// 프로젝트 모집 언어가 끝나면 탈출
			}
			boardDTOList.add(new BoardDTO(boardVO, boardField, boardLanguage));
		}
		long end = System.currentTimeMillis();
		log.info(Long.toString(end - start));
		return boardDTOList;
	}

	@Override
	public void register(BoardVO boardVO, int[] boardField, int[] boardLanguage) {
		boardVO.setReg_date(new Timestamp(System.currentTimeMillis()).toString());
		bMapper.register(boardVO);
		int bid=bMapper.selectBid(boardVO.getReg_date());
		fMapper.registerBoardField(bid, boardField);
		lMapper.registerBoardLanguage(bid, boardLanguage);
	}

	@Override
	public List<BoardDTO> selectSuitableBoardList(int[] fieldList, int[] languageList) {
		long start = System.currentTimeMillis();
		List<BoardVO> boardList = bMapper.selectSuitableBoardList(fieldList, languageList);
		if (boardList.size() == 0)// 프로젝트가 없으면 null 반환
			return null;
		List<Integer> bidList = new ArrayList<Integer>();
		int rowNum = 1;
		for (BoardVO boardVO : boardList) {
			bidList.add(boardVO.getBid());
			boardVO.setRowNum(rowNum);
			rowNum++;
		}
		List<BoardDTO> boardDTOList = new ArrayList<BoardDTO>();// O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		Queue<Conn_bfVO> conn_bfList = fMapper.selectSuitableConn_bfList(bidList);
		Queue<Conn_blVO> conn_blList = lMapper.selectSuitableConn_blList(bidList);
		for (BoardVO boardVO : boardList) {
			int bid = boardVO.getBid();
			List<FieldVO> boardField = new ArrayList<FieldVO>();
			List<LanguageVO> boardLanguage = new ArrayList<LanguageVO>();
			while (!conn_bfList.isEmpty()) {
				Conn_bfVO conn_bfVO = conn_bfList.peek();
				if (conn_bfVO.getBid() == bid) {
					boardField.add(fMapper.select(conn_bfVO.getFid()));
					conn_bfList.remove();
				} else
					break;// 프로젝트 모집 분야가 끝나면 탈출
			}
			while (!conn_blList.isEmpty()) {
				Conn_blVO conn_blVO = conn_blList.peek();
				if (conn_blVO.getBid() == bid) {
					boardLanguage.add(lMapper.select(conn_blVO.getLid()));
					conn_blList.remove();
				} else
					break;// 프로젝트 모집 언어가 끝나면 탈출
			}
			boardDTOList.add(new BoardDTO(boardVO, boardField, boardLanguage));
		}
		long end = System.currentTimeMillis();
		log.info(Long.toString(end - start));
		return boardDTOList;
	}

	@Override
	public BoardDTO select(int bid) {
		BoardVO board = bMapper.select(bid);
		Queue<Conn_bfVO> conn_bfList = fMapper.selectBoardConn_bfList(board.getBid());
		Queue<Conn_blVO> conn_blList = lMapper.selectBoardConn_blList(board.getBid());
		List<FieldVO> boardField = new ArrayList<FieldVO>();
		List<LanguageVO> boardLanguage = new ArrayList<LanguageVO>();
		for (Conn_bfVO conn_bfVO : conn_bfList)
			boardField.add(fMapper.select(conn_bfVO.getFid()));
		for (Conn_blVO conn_blVO : conn_blList)
			boardLanguage.add(lMapper.select(conn_blVO.getLid()));
		return new BoardDTO(board, boardField, boardLanguage);
	}

	@Override
	public void update(BoardVO boardVO, List<Integer> boardField, List<Integer> boardLanguage) {
		bMapper.update(boardVO);
		int bid = boardVO.getBid();
		fMapper.updateBoardField(bid, boardField);
		lMapper.updateBoardLanguage(bid, boardLanguage);
	}

	@Override
	public void delete(int bid) {
		fMapper.deleteConn_bf(bid);
		lMapper.deleteConn_bl(bid);
		bMapper.delete(bid);
	}

	@Override
	public List<BoardDTO> selectMyProjectList(int uid) {
		long start = System.currentTimeMillis();
		List<BoardVO> boardList = bMapper.selectMyProjectList(uid);
		if (boardList.size() == 0)// 프로젝트가 없으면 null 반환
			return null;
		List<Integer> bidList = new ArrayList<Integer>();
		int rowNum = 1;
		for (BoardVO boardVO : boardList) {
			bidList.add(boardVO.getBid());
			boardVO.setRowNum(rowNum);
			rowNum++;
		}
		List<BoardDTO> boardDTOList = new ArrayList<BoardDTO>();// O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		Queue<Conn_bfVO> conn_bfList = fMapper.selectSuitableConn_bfList(bidList);
		Queue<Conn_blVO> conn_blList = lMapper.selectSuitableConn_blList(bidList);
		for (BoardVO boardVO : boardList) {
			int bid = boardVO.getBid();
			List<FieldVO> boardField = new ArrayList<FieldVO>();
			List<LanguageVO> boardLanguage = new ArrayList<LanguageVO>();
			while (!conn_bfList.isEmpty()) {
				Conn_bfVO conn_bfVO = conn_bfList.peek();
				if (conn_bfVO.getBid() == bid) {
					boardField.add(fMapper.select(conn_bfVO.getFid()));
					conn_bfList.remove();
				} else
					break;// 프로젝트 모집 분야가 끝나면 탈출
			}
			while (!conn_blList.isEmpty()) {
				Conn_blVO conn_blVO = conn_blList.peek();
				if (conn_blVO.getBid() == bid) {
					boardLanguage.add(lMapper.select(conn_blVO.getLid()));
					conn_blList.remove();
				} else
					break;// 프로젝트 모집 언어가 끝나면 탈출
			}
			boardDTOList.add(new BoardDTO(boardVO, boardField, boardLanguage));
		}
		long end = System.currentTimeMillis();
		log.info(Long.toString(end - start));
		return boardDTOList;
	}
}
