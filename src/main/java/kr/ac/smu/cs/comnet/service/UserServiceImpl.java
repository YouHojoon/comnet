package kr.ac.smu.cs.comnet.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.smu.cs.comnet.dao.FieldDAO;
import kr.ac.smu.cs.comnet.dao.LanguageDAO;
import kr.ac.smu.cs.comnet.dao.UserDAO;
import kr.ac.smu.cs.comnet.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO uDAO;
	@Autowired
	private FieldDAO fDAO;
	@Autowired
	private LanguageDAO lDAO;
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	public void register(UserVO userVO, int[] user_field, int[] user_language) {
		userVO.setPassword(bcryptPasswordEncoder.encode(userVO.getPassword()));
		uDAO.register(userVO);
		int uid=uDAO.select(userVO.getEmail()).getUid();
		for(int fid : user_field)
			fDAO.registerUserField(uid, fid);
		for(int lid : user_language)
			lDAO.regiserUserLanguage(uid, lid);
	}
	@Override
	public String auth(String email) {
		if(uDAO.select(email)!=null) {
			return "duplication";//email 중복
		}
		StringBuffer authString=new StringBuffer();
		for(int i=0; i<6; i++) 
			authString.insert(i, (char)((int)(Math.random()*26)+65));//랜덤 문자열 A~Z 6자리 전송
		try {
			MimeMessage authMail= javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(authMail,true,"UTF-8");
			messageHelper.setFrom("dbghwns11@gmail.com");
			messageHelper.setTo(email+"@sangmyung.kr");
			messageHelper.setSubject("인증메일 테스트");
			messageHelper.setText(authString.toString());
			javaMailSender.send(authMail);
		}catch(Exception e) {e.printStackTrace();}
		return authString.toString();
	}
}
