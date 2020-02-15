package kr.ac.smu.cs.comnet.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.dao.BoardDAO;
import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO dao;
	@Autowired
	FieldService fService;
	@Autowired
	LanguageService lService;
	Logger log=LoggerFactory.getLogger(BoardServiceImpl.class);
	@Override
	public List<BoardDTO> selectList() {
		long start=System.currentTimeMillis();
		List<BoardVO> boardList=dao.selectList();
		List<BoardDTO> boardDTOList=new ArrayList<BoardDTO>();
		for(BoardVO tmp : boardList) {
			boardDTOList.add(new BoardDTO(tmp,fService.selectBoardField(tmp.getBid())
					,lService.selectBoardLanguage(tmp.getBid())));
		}
		long end=System.currentTimeMillis();
		log.info(Long.toString(end-start));
		return boardDTOList;
	}
}
