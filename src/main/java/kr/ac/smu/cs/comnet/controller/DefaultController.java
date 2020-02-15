package kr.ac.smu.cs.comnet.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;

@Controller
public class DefaultController {
	Logger log=LoggerFactory.getLogger(DefaultController.class);
	@Autowired
	private FieldService fService;
	@Autowired
	private LanguageService lService;
	@Autowired
	private BoardService bService;
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	@GetMapping("/loginPage")
	public String login(@CookieValue(name = "remember-me", required = false) Cookie auto, 
			HttpServletResponse response) {
		if (auto != null) {
			auto.setMaxAge(2592000);//쿠키 존재 시 쿠키의 유효기간을 갱신
			response.addCookie(auto);
			return "redirect:/board";
		}
		return "/loginPage";
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/board")
	public void board(Model model) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
		model.addAttribute("boardList",bService.selectList());
	}
	@GetMapping("/register")
	public void register(Model model) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
	}
	@GetMapping("/auth")
	public @ResponseBody StringBuffer auth(@RequestParam("email") String email) {
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
		return authString;
	}
}
