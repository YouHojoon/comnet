package kr.ac.smu.cs.comnet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.dto.UserDTO;
import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;
import kr.ac.smu.cs.comnet.service.UserService;
import kr.ac.smu.cs.comnet.vo.UserVO;

@Controller
@SessionAttributes({"selectFieldList", "selectLanguageList","authString"})
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
	public String login() {
		return "/loginPage";
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/board")
	public void board(Model model,HttpSession session) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
		int[] selectFieldList = (int[])session.getAttribute("selectFieldList");
		int[] selectLanguageList= (int[])session.getAttribute("selectLanguageList");
		List<BoardDTO> boardList= null;
		if(selectFieldList != null || selectLanguageList != null) //언어나 영역이 선택되어 있을 시에
			boardList = bService.selectSuitableBoardList(selectFieldList, selectLanguageList);
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
	public void board(@RequestParam(name = "selectFieldList", required = false) int[] selectFieldList ,
			@RequestParam(name = "selectLanguageList", required = false) int[] selectLanguageList, Model model){
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
		List<BoardDTO> boardList = bService.selectSuitableBoardList(selectFieldList, selectLanguageList);
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
	public @ResponseBody boolean auth(@RequestParam("email") String email, Model model, HttpServletRequest request) {
		String requestUrl=request.getHeader("referer");
		String authString=uService.auth(email,requestUrl.substring(requestUrl.lastIndexOf("/")));
		if(authString.equals("no"))
			return false;
		else if(authString.equals("duplication"))
			return false;
		else {
			model.addAttribute("authString", authString);
			return true;
		}
	}
	@PostMapping("/auth")
	public @ResponseBody boolean auth(@RequestParam("authString") String authString, HttpSession session, SessionStatus sessionStatus) {
		if(((String)session.getAttribute("authString")).equals(authString)) {
			sessionStatus.setComplete();
			return true;
		}
		else {
			sessionStatus.setComplete();
			return false;
		}
	}
	@PostMapping("/register")
	public void register(UserVO userVO, @RequestParam("userField") int[] userField, 
			@RequestParam("userLanguage") int[] userLanguage, @CookieValue(name = "remember-me", required = false) Cookie auto,
			HttpServletResponse response) throws IOException{
		uService.register(userVO,userField,userLanguage);
		if (auto != null) //로그인 되어 있다면 회원가입 페이지로 들어가지 못하게 막음
			response.sendRedirect("/board");
		
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/mypage")
	public void mypage(Model model) {}
	
	@GetMapping("/findpw")
	public void findpw() {}
	
	@PatchMapping("/findpw")
	public @ResponseBody void findpw(@RequestParam("email") String email, @RequestBody String password) {
		uService.changePassword(email, password);
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/volunteerDetail")
	public @ResponseBody UserDTO volunteerDetail(@RequestParam("vid") int vid){
		return uService.userDetail(vid);
	}
}
