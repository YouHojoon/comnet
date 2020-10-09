package kr.ac.smu.cs.comnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.mapper.MessageMapper;
import kr.ac.smu.cs.comnet.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageMapper mapper;
	
	@Override
	public void createMessage(MessageVO messageVO) {
		mapper.createMessage(messageVO);
	}
	@Override
	public void deleteMessage(int mid) {
		mapper.deleteMessage(mid);
	}
	@Override
	public List<MessageVO> selectMessageList(int uid) {
		return mapper.selectMessageList(uid);
	}

}
