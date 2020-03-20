package kr.ac.smu.cs.comnet.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import kr.ac.smu.cs.comnet.mapper.FieldMapper;
import kr.ac.smu.cs.comnet.mapper.LanguageMapper;
import kr.ac.smu.cs.comnet.mapper.UserMapper;
import kr.ac.smu.cs.comnet.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper uMapper;
	@Autowired
	private FieldMapper fMapper;
	@Autowired
	private LanguageMapper lMapper;
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	public void register(UserVO userVO, int[] userField, int[] userLanguage) {
		userVO.setPassword(bcryptPasswordEncoder.encode(userVO.getPassword()));
		uMapper.register(userVO);
		int uid=uMapper.selectByEmail(userVO.getEmail()).getUid();
		for(int fid : userField)
			fMapper.registerUserField(uid, fid);
		for(int lid : userLanguage)
			lMapper.registerUserLanguage(uid, lid);
	}
	@Override
	public String auth(String email, String requestUrl) {
		if(requestUrl.equals("/register") && uMapper.selectByEmail(email)!=null) {
			return "duplication";//email 중복
		}
		else if(requestUrl.equals("/findpw") && uMapper.selectByEmail(email)==null) {
			return "no";
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
	@Override
	public UserVO select(int uid) {
		return uMapper.select(uid);
	}
	@Override
	public void changePassword(String email, String password) {
		uMapper.changePassword(email, bcryptPasswordEncoder.encode(password));
	}
	@Override
	public void update(UserVO userVO, List<Integer> userField, List<Integer> userLanguage) {
		uMapper.update(userVO);
		int uid=userVO.getUid();
		fMapper.deleteConn_uf(uid);
		lMapper.deleteConn_ul(uid);
		for(int fid: userField)
			fMapper.registerUserField(uid, fid);
		for(int lid: userLanguage)
			lMapper.registerUserLanguage(uid, lid);
	}
}
