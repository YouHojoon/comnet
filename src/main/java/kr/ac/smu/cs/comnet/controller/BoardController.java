package kr.ac.smu.cs.comnet.controller;


import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Base64.Decoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
import kr.ac.smu.cs.comnet.vo.BoardVO;


@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/board")
@SessionAttributes("redirectUrl")
public class BoardController {
	@Autowired
	private FieldService fService;
	@Autowired
	private LanguageService lService;
	@Autowired
	private BoardService bService;
	@Autowired
	private UserService uService;
	
	Logger log= LoggerFactory.getLogger(BoardController.class);
	@InitBinder
	public void dateBinder(WebDataBinder binder) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "deadline", new CustomDateEditor(sdf, true));
	}	
	
	@GetMapping("/new")
	public void newBoard(Model model) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
	}
	@PostMapping("/new")
	public void newBoard(BoardVO boardVO, @RequestParam("boardField") int[] boardField, @RequestParam("boardLanguage") int[] boardLanguage) {
		bService.register(boardVO, boardField, boardLanguage);
	}
	@GetMapping("/view")
	public void view(@RequestParam("bid") int bid, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String redirectUrl= request.getHeader("referer");//요청했던 url
		Cookie[] cookieList = request.getCookies();
		String email= null;
		for(Cookie c : cookieList) {
			if(c.getName().equals("remember-me")) {
				Decoder decoder = Base64.getDecoder();
				email= new String(decoder.decode(c.getValue()),"UTF-8");
				email=email.substring(0,email.indexOf(":"));//쿠키에서 email을 가져옴 ex:201611011
			}
		}
		BoardDTO board = bService.select(bid, email);
		model.addAttribute("board", board);
		model.addAttribute("owner", uService.select(board.getBoardVO().getUid()));
		//지원자 수
		List<UserDTO> volunteerList=board.getVolunteerList();
		List<UserDTO> partnerList=board.getPartnerList();
		if(partnerList!=null)
			model.addAttribute("partnerTotal", partnerList.size());
		else
			model.addAttribute("partnerTotal", 0);
		
		if(volunteerList!=null)
			model.addAttribute("volunteerTotal", volunteerList.size());
		else
			model.addAttribute("volunteerTotal", 0);
		
		if(redirectUrl!=null && (redirectUrl.substring(21).equals("/board") 
				|| redirectUrl.contains("/mypage/myproject")))//back버튼으로 돌아갈 때 원래 있던 곳으로 돌아가기 위해서 요청 url 저장
			model.addAttribute("redirectUrl",redirectUrl.substring(21));
		else {
			model.addAttribute("redirectUrl","/board");
		}

	}
	@GetMapping("/update")
	public void update(@RequestParam("bid") int bid, Model model) {
		BoardDTO board=bService.selectMyProject(bid);
		model.addAttribute("board", board);
		model.addAttribute("fieldList",fService.selectList());
		model.addAttribute("languageList", lService.selectList());
		List<UserDTO> partnerList=board.getPartnerList();
		if(partnerList!=null)
			model.addAttribute("partnerTotal", partnerList.size());
		else
			model.addAttribute("partnerTotal", 0);
	}
	@PutMapping("/update")
	public @ResponseBody void update(@RequestParam("bid") int bid, @RequestBody Map<String, Object> json, 
			HttpSession session, SessionStatus sessionStatus) throws java.text.ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date= new Date(sdf.parse((String)json.get("deadline")).getTime());
		BoardVO boardVO= new BoardVO(bid,(int)json.get("uid"),(String)json.get("title"),
				(String)json.get("content"),date,(int)json.get("partner_limit"),(String)json.get("contact"));
		bService.update(boardVO, (List<Integer>)json.get("boardField"), (List<Integer>)json.get("boardLanguage"));
	
	}
	@DeleteMapping("/delete")
	public @ResponseBody String delete(@RequestParam("bid") int bid, HttpSession session, SessionStatus sessionStatus) {
		bService.delete(bid);
		String redirectUrl=session.getAttribute("redirectUrl").toString();
		sessionStatus.setComplete();
		return redirectUrl;//요청했던 url전송
	}
	@PostMapping("/apply")
	public @ResponseBody void apply(@RequestBody Map<String, Integer> json) {
		bService.applyToProject(json.get("bid"), json.get("vid"));
	}
	@DeleteMapping("/applyCancel")
	public @ResponseBody void applyCancel(@RequestParam("bid") int bid, HttpServletRequest request) throws UnsupportedEncodingException{
		Cookie[] cookieList = request.getCookies();
		String email= null;
		for(Cookie c : cookieList) {
			if(c.getName().equals("remember-me")) {
				Decoder decoder = Base64.getDecoder();
				email= new String(decoder.decode(c.getValue()),"UTF-8");
				email=email.substring(0,email.indexOf(":"));//쿠키에서 email을 가져옴 ex:201611011
			}
		}
		bService.applyCancel(bid, email);
	}
	@PostMapping("/approval")
	public @ResponseBody void agree(@RequestParam("vid") int vid, @RequestParam("bid") int bid) {
		bService.agree(bid, vid);
	}
	@DeleteMapping("/reject")
	public @ResponseBody void reject(@RequestParam("bid") int bid, @RequestParam("vid") int vid) {
		bService.applyCancelByVid(bid, vid);
	}
}
