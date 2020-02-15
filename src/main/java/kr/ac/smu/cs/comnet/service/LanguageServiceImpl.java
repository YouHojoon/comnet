package kr.ac.smu.cs.comnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.dao.LanguageDAO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

@Service
public class LanguageServiceImpl implements LanguageService{
	@Autowired
	LanguageDAO dao;
	
	@Override
	public List<LanguageVO> selectList() {
		return dao.selectList();
	}
	@Override
	public List<LanguageVO> selectBoardLanguage(int bid) {
		return dao.selectBoardLanguage(bid);
	}
}
