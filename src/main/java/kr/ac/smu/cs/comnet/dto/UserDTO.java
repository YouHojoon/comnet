package kr.ac.smu.cs.comnet.dto;

import java.util.List;

import kr.ac.smu.cs.comnet.vo.FieldVO;
import kr.ac.smu.cs.comnet.vo.LanguageVO;
import kr.ac.smu.cs.comnet.vo.UserVO;

public class UserDTO {
	private UserVO userVO;
	private List<FieldVO> userField;
	private List<LanguageVO> userLanguage;
	
	public UserDTO(UserVO userVO, List<FieldVO> userField, List<LanguageVO> userLanguage) {
		this.userVO = userVO;
		this.userField = userField;
		this.userLanguage = userLanguage;
	}
	
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	public List<FieldVO> getUserField() {
		return userField;
	}
	public void setUserField(List<FieldVO> userField) {
		this.userField = userField;
	}
	public List<LanguageVO> getUserLanguage() {
		return userLanguage;
	}
	public void setUserLanguage(List<LanguageVO> userLanguage) {
		this.userLanguage = userLanguage;
	}
	
}
