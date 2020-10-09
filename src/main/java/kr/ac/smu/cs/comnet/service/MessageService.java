package kr.ac.smu.cs.comnet.service;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.MessageVO;

public interface MessageService {
	
	public void createMessage(MessageVO messageVO);
	
	public void deleteMessage(int mid);
	
	public List<MessageVO> selectMessageList(int uid);
}
