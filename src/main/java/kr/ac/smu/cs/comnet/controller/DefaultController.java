package kr.ac.smu.cs.comnet.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
			auto.setMaxAge(2592000);//��Ű ���� �� ��Ű�� ��ȿ�Ⱓ�� ����
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
		int[] selectFieldList = (int[])session.getAttribute("selectFieldList");
		int[] selectLanguageList= (int[])session.getAttribute("selectLanguageList");
		List<BoardDTO> boardList= null;
		if(selectFieldList != null || selectLanguageList != null) //�� ������ ���õǾ� ���� �ÿ�
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
		model.addAttribute("selectFieldList", selectFieldList);//����� ���� ���� ǥ�ÿ� �� ��ȸ �� Back ��ư ������ ���� ���� �������� ����
		model.addAttribute("selectLanguageList", selectLanguageList);//����� ���� ���� ǥ�ÿ� �� ��ȸ �� Back ��ư ������ ���� ���� �������� ����
	}
	@GetMapping("/register")
	public void register(Model model) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
	}
	@GetMapping("/auth")
	public @ResponseBody String auth(@RequestParam("email") String email, HttpServletRequest request) {
		String requestUrl=request.getHeader("referer");
		return uService.auth(email,requestUrl.substring(requestUrl.indexOf("/")));
	}
	@PostMapping("/register")
	public void register(UserVO userVO, @RequestParam("userField") int[] userField, 
			@RequestParam("userLanguage")int[] userLanguage, @CookieValue(name = "remember-me", required = false) Cookie auto,
			HttpServletResponse response) throws IOException{
		uService.register(userVO,userField,userLanguage);
		if (auto != null) //�α��� �Ǿ� �ִٸ� ȸ������ �������� ���� ���ϰ� ����
			response.sendRedirect("/board");
		
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/mypage")
	public void mypage(Model model) {}
	
	@GetMapping("/findpw")
	public void findpw() {}
	
	@PatchMapping("/findpw")
	public @ResponseBody void findpw(@RequestBody Map<String, String> json) {
		uService.changePassword(json.get("email"), json.get("password"));
	}
}
