package kr.ac.smu.cs.comnet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;
import kr.ac.smu.cs.comnet.service.UserService;
import kr.ac.smu.cs.comnet.vo.UserVO;


@Controller
@RequestMapping("/mypage")
@PreAuthorize("isAuthenticated()")
public class MypageController {
	@Autowired
	private BoardService bService;
	@Autowired
	private FieldService fService;
	@Autowired
	private LanguageService lService;
	@Autowired
	private UserService uService;
	Logger log= LoggerFactory.getLogger(MypageController.class);
	@GetMapping("/myproject")
	public void myproject(@RequestParam("uid") int uid, Model model) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
		List<BoardDTO> boardList= bService.selectMyProjectList(uid);
		model.addAttribute("boardList",boardList);
		if(boardList!=null)
			model.addAttribute("total",boardList.size());
		else
			model.addAttribute("total",0);
	}
	@GetMapping("/info")
	public void info(@RequestParam("uid") int uid, Model model) {
		model.addAttribute("user", uService.select(uid));
		model.addAttribute("userField", fService.selectUserField(uid));
		model.addAttribute("userLanguage", lService.selectUserLanguage(uid));
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
	}
	@PutMapping("/info")
	public @ResponseBody void update(@RequestParam("uid") int uid, @RequestBody Map<String, Object> json) {
		UserVO userVO=new UserVO(uid,(String)json.get("email")
				,(String)json.get("name"),(String)json.get("memo"),(String)json.get("phone"));
		uService.update(userVO, (List<Integer>)json.get("userField"), (List<Integer>)json.get("userLanguage"));
	}
	@DeleteMapping("/leave")
	public @ResponseBody void leave(@RequestParam("uid") int uid, HttpServletRequest request,
			HttpServletResponse response,SessionStatus sessionStatus) {
		uService.delete(uid);
		Cookie[] cookieList=request.getCookies();
		for(Cookie cookie : cookieList) {//모든 쿠키 만료
			cookie.setMaxAge(0);
			cookie.setValue(null);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		sessionStatus.setComplete();//모든 세션 만료
	}
}
