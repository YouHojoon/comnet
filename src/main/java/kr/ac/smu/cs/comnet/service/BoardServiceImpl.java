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
import kr.ac.smu.cs.comnet.vo.FieldVO;

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
	@Cacheable(cacheNames  = "BoardCache" )
	public List<BoardDTO> selectList() {
		long start=System.currentTimeMillis();
		List<BoardVO> boardList=bDAO.selectList();
		List<BoardDTO> boardDTOList=new ArrayList<BoardDTO>();//O(n)만큼 걸려서 데이터가 많아지면 시간 오래 걸림
		long forstart=System.currentTimeMillis();
		for(BoardVO tmp : boardList) {
			//프로젝트 분야와 언어를 조회하기 위해 DTO를 만듬
			boardDTOList.add(new BoardDTO(tmp,fDAO.selectBoardField(tmp.getBid())
					,lDAO.selectBoardLanguage(tmp.getBid())));
		}
		long forend=System.currentTimeMillis();
		log.info(Long.toString(forend-forstart));
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
