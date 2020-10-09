package kr.ac.smu.cs.comnet.mapper;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.MessageVO;

public interface MessageMapper {
	public void createMessage(MessageVO messageVO);
	
	public void deleteMessage(int mid);
	
	public void deleteByUid(int uid);
	
	public List<MessageVO> selectMessageList(int uid);
}
