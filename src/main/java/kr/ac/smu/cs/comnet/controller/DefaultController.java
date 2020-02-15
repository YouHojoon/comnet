package kr.ac.smu.cs.comnet.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;

@Controller
public class DefaultController {
	@Autowired
	FieldService fService;
	@Autowired
	LanguageService lService;
	@Autowired
	BoardService bService;
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

}
