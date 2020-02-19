package kr.ac.smu.cs.comnet.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.smu.cs.comnet.mapper.LanguageMapper;
import kr.ac.smu.cs.comnet.vo.LanguageVO;

@Repository
public class LanguageDAOImpl implements LanguageDAO{
	@Autowired
	private LanguageMapper mapper;
	
	@Override
	public List<LanguageVO> selectList() {
		return mapper.selectList();
	}
	@Override
	public List<LanguageVO> selectBoardLanguage(int bid) {
		return mapper.selectBoardLanguage(bid);
	}
	@Override
	public void regiserUserLanguage(int uid, int lid) {
		mapper.registerUserLanguage(uid, lid);
	}
}
