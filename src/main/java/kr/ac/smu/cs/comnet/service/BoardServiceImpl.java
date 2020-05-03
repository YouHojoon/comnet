package kr.ac.smu.cs.comnet.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.smu.cs.comnet.mapper.BoardMapper;
import kr.ac.smu.cs.comnet.mapper.FieldMapper;
import kr.ac.smu.cs.comnet.mapper.LanguageMapper;
import kr.ac.smu.cs.comnet.mapper.UserMapper;
import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.dto.UserDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;
import kr.ac.smu.cs.comnet.vo.Conn_bfVO;
import kr.ac.smu.cs.comnet.vo.Conn_blVO;
import kr.ac.smu.cs.comnet.vo.Conn_bvVO;
import kr.ac.smu.cs.comnet.vo.Conn_ubVO;
import kr.ac.smu.cs.comnet.vo.Conn_ufVO;
import kr.ac.smu.cs.comnet.vo.Conn_ulVO;
import kr.ac.smu.cs.comnet.vo.FieldVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;
import kr.ac.smu.cs.comnet.vo.UserVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper bMapper;
	@Autowired
	private FieldMapper fMapper;
	@Autowired
	private LanguageMapper lMapper;
	@Autowired
	private UserMapper uMapper;
	private Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private BoardDTO bindBoardDTO(BoardVO boardVO,Queue<Conn_bfVO> conn_bfList, Queue<Conn_blVO> conn_blList) {
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
		return new BoardDTO(boardVO,boardField,boardLanguage);
	}
	
	@Override
	public List<BoardDTO> selectList() {
		long start = System.currentTimeMillis();
		List<BoardVO> boardList = bMapper.selectList();
		if (boardList.size() == 0)// 프로젝트가 없으면 null 반환
			return null;
		List<BoardDTO> boardDTOList = new ArrayList<BoardDTO>();// O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		Queue<Conn_bfVO> conn_bfList = fMapper.selectConn_bfList();
		Queue<Conn_blVO> conn_blList = lMapper.selectConn_blList();
		for (BoardVO boardVO : boardList) 
			boardDTOList.add(bindBoardDTO(boardVO, conn_bfList, conn_blList));
		long end = System.currentTimeMillis();
		log.info(Long.toString(end - start));
		return boardDTOList;
	}

	@Override
	@Transactional
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
		for (BoardVO boardVO : boardList) 
			boardDTOList.add(bindBoardDTO(boardVO, conn_bfList, conn_blList));
		long end = System.currentTimeMillis();
		log.info(Long.toString(end - start));
		return boardDTOList;
	}

	@Override
	public BoardDTO select(int bid, String email) {
		BoardVO boardVO = bMapper.select(bid);
		Queue<Conn_bfVO> conn_bfList = fMapper.selectBoardConn_bfList(boardVO.getBid());
		Queue<Conn_blVO> conn_blList = lMapper.selectBoardConn_blList(boardVO.getBid());
		List<FieldVO> boardField = new ArrayList<FieldVO>();
		List<LanguageVO> boardLanguage = new ArrayList<LanguageVO>();
		for (Conn_bfVO conn_bfVO : conn_bfList)
			boardField.add(fMapper.select(conn_bfVO.getFid()));
		for (Conn_blVO conn_blVO : conn_blList)
			boardLanguage.add(lMapper.select(conn_blVO.getLid()));
		
		//지원자 목록
		List<Conn_bvVO> conn_bvList = bMapper.selectConn_bvList(bid);
		List<Conn_ubVO> conn_ubList = bMapper.selectConn_ubList(bid);
		List<UserDTO> volunteerList = new ArrayList<UserDTO>();
		List<UserDTO> partnerList = new ArrayList<UserDTO>();
		
		UserVO userVO=uMapper.selectByEmail(email);
		boolean applied=false;//지원 여부
		boolean partner=false;//팀원 여부
		if(conn_bvList!=null) {
			for(Conn_bvVO conn_bvVO : conn_bvList) {
				List<FieldVO> userField= new ArrayList<FieldVO>();
				List<LanguageVO> userLanguage = new ArrayList<LanguageVO>();
				int vid=conn_bvVO.getVid();
				if(vid==userVO.getUid())applied=true;
				List<Conn_ufVO> conn_ufVOList= fMapper.selectUserField(vid);
				List<Conn_ulVO> conn_ulVOList= lMapper.selectUserLanguage(vid);
				for(Conn_ufVO conn_ufVO : conn_ufVOList) 
					userField.add(fMapper.select(conn_ufVO.getFid()));
				for(Conn_ulVO conn_ulVO: conn_ulVOList)
					userLanguage.add(lMapper.select(conn_ulVO.getLid()));
				volunteerList.add(new UserDTO(uMapper.select(vid), userField, userLanguage));
			}
		}
			if(conn_ubList!=null) {
				for(Conn_ubVO conn_ubVO : conn_ubList) {
					List<FieldVO> userField= new ArrayList<FieldVO>();
					List<LanguageVO> userLanguage = new ArrayList<LanguageVO>();
					int uid=conn_ubVO.getUid();
					if(uid==userVO.getUid())partner=true;
					List<Conn_ufVO> conn_ufVOList= fMapper.selectUserField(uid);
					List<Conn_ulVO> conn_ulVOList= lMapper.selectUserLanguage(uid);
					for(Conn_ufVO conn_ufVO : conn_ufVOList) 
						userField.add(fMapper.select(conn_ufVO.getFid()));
					for(Conn_ulVO conn_ulVO: conn_ulVOList)
						userLanguage.add(lMapper.select(conn_ulVO.getLid()));
					partnerList.add(new UserDTO(uMapper.select(uid), userField, userLanguage));
				}
		}
		
		return new BoardDTO(boardVO, boardField, boardLanguage,volunteerList, partnerList,applied,partner);
	}

	@Override
	@Transactional
	public void update(BoardVO boardVO, List<Integer> boardField, List<Integer> boardLanguage) {
		bMapper.update(boardVO);
		int bid = boardVO.getBid();
		fMapper.updateBoardField(bid, boardField);
		lMapper.updateBoardLanguage(bid, boardLanguage);
	}

	@Override
	@Transactional
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
		for (BoardVO boardVO : boardList) 
			boardDTOList.add(bindBoardDTO(boardVO, conn_bfList, conn_blList));
		long end = System.currentTimeMillis();
		log.info(Long.toString(end - start));
		return boardDTOList;
	}
	
	@Override
	public void applyToProject(int bid, int vid) {
		bMapper.applyToProject(bid, vid);	
	}
	@Override
	public BoardDTO selectMyProject(int bid) {
		BoardVO boardVO = bMapper.select(bid);
		Queue<Conn_bfVO> conn_bfList = fMapper.selectBoardConn_bfList(boardVO.getBid());
		Queue<Conn_blVO> conn_blList = lMapper.selectBoardConn_blList(boardVO.getBid());
		List<FieldVO> boardField = new ArrayList<FieldVO>();
		List<LanguageVO> boardLanguage = new ArrayList<LanguageVO>();
		for (Conn_bfVO conn_bfVO : conn_bfList)
			boardField.add(fMapper.select(conn_bfVO.getFid()));
		for (Conn_blVO conn_blVO : conn_blList)
			boardLanguage.add(lMapper.select(conn_blVO.getLid()));
		return new BoardDTO(boardVO, boardField, boardLanguage);
	}
	@Override
	public void applyCancel(int bid, String email) {
		bMapper.applyCancel(bid,uMapper.selectByEmail(email).getUid());
	}
	@Override
	@Transactional
	public void agree(int bid, int vid) {
		bMapper.applyCancel(bid, vid);
		bMapper.approval(bid, vid);
	}
}
