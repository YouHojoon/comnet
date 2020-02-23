package kr.ac.smu.cs.comnet.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;
import kr.ac.smu.cs.comnet.service.UserService;
import kr.ac.smu.cs.comnet.vo.UserVO;

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
	private UserService uService;
	@GetMapping("/")
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
		List<BoardDTO> boardList = bService.selectList();
		model.addAttribute("boardList",boardList);
		model.addAttribute("total",boardList.size());
	}
	@GetMapping("/register")
	public void register(Model model) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
	}
	@GetMapping("/auth")
	public @ResponseBody String auth(@RequestParam("email") String email) {
		return uService.auth(email);
	}
	@PostMapping("/register")
	public void register(UserVO userVO, @RequestParam("user_field") int[] user_field, @RequestParam("user_language")int[] user_language) {
		uService.register(userVO,user_field,user_language);
		
	}
}
