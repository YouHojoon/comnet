package kr.ac.smu.cs.comnet.controller;

import java.util.List;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;
import kr.ac.smu.cs.comnet.service.UserService;
import kr.ac.smu.cs.comnet.vo.UserVO;

@Controller
@SessionAttributes({"selectFieldList", "selectLanguageList"})
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
	public void board(Model model,HttpSession session) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
		List<Integer> selectFieldList = (List<Integer>)session.getAttribute("selectFieldList");
		List<Integer> selectLanguageList= (List<Integer>)session.getAttribute("selectLanguageList");
		List<BoardDTO> boardList= null;
		if(selectFieldList != null || selectLanguageList != null) 
			boardList = bService.selectSuitableList(selectFieldList, selectLanguageList);
		else
			boardList = bService.selectList();
		model.addAttribute("boardList",boardList);
		if(boardList!=null)
			model.addAttribute("total",boardList.size());
		else
			model.addAttribute("total",0);
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/board")
	public void board(@RequestParam(name = "selectFieldList", required = false) List<Integer> selectFieldList ,
			@RequestParam(name = "selectLanguageList", required = false) List<Integer> selectLanguageList, Model model){
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
		List<BoardDTO> boardList = bService.selectSuitableList(selectFieldList, selectLanguageList);
		model.addAttribute("boardList",boardList);
		if(boardList!=null)
			model.addAttribute("total",boardList.size());
		else
			model.addAttribute("total",0);
		model.addAttribute("selectFieldList", selectFieldList);//사용자 선택 영역 표시와 상세 조회 후 Back 버튼 눌었을 때를 위해 세션으로 유지
		model.addAttribute("selectLanguageList", selectLanguageList);//사용자 선택 영역 표시와 상세 조회 후 Back 버튼 눌었을 때를 위해 세션으로 유지
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
