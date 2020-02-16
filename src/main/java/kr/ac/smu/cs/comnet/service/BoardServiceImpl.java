package kr.ac.smu.cs.comnet.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.dao.BoardDAO;
import kr.ac.smu.cs.comnet.dao.FieldDAO;
import kr.ac.smu.cs.comnet.dao.LanguageDAO;
import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.vo.BoardVO;

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
		List<BoardDTO> boardDTOList=new ArrayList<BoardDTO>();
		for(BoardVO tmp : boardList) {
			//프로젝트 분야와 언어를 조회하기 위해 DTO를 만듬
			boardDTOList.add(new BoardDTO(tmp,fDAO.selectBoardField(tmp.getBid())
					,lDAO.selectBoardLanguage(tmp.getBid())));
		}
		long end=System.currentTimeMillis();
		log.info(Long.toString(end-start));
		return boardDTOList;
	}
}
