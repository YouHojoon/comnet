package kr.ac.smu.cs.comnet.controller;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.security.access.prepost.PreAuthorize;
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
	public void view(@RequestParam("bid") int bid, Model model, HttpServletRequest request) {
		String redirectUrl= request.getHeader("referer");//夸没沁带 url
		BoardDTO board = bService.select(bid);
		model.addAttribute("board", board);
		model.addAttribute("owner", uService.select(board.getBoardVO().getUid()));
		model.addAttribute("redirectUrl",redirectUrl.substring(21));
	}
	@GetMapping("/update")
	public void update(@RequestParam("bid") int bid, Model model) {
		model.addAttribute("board", bService.select(bid));
		model.addAttribute("fieldList",fService.selectList());
		model.addAttribute("languageList", lService.selectList());
	}
	@PutMapping("/update")
	public @ResponseBody String update(@RequestParam("bid") int bid, @RequestBody Map<String, Object> json, 
			HttpSession session, SessionStatus sessionStatus) throws java.text.ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date= new Date(sdf.parse((String)json.get("deadline")).getTime());
		BoardVO boardVO= new BoardVO(bid,(int)json.get("uid"),(String)json.get("title"),
				(String)json.get("content"),date,(int)json.get("partner_limit"),(String)json.get("contact"));
		bService.update(boardVO, (int[])json.get("boardField"), (int[])json.get("boardLanguage"));
		String redirectUrl=session.getAttribute("redirectUrl").toString();
		sessionStatus.setComplete();
		return redirectUrl;//夸没沁带 url傈价
	}
	@DeleteMapping("/delete")
	public @ResponseBody String delete(@RequestParam("bid") int bid, HttpSession session, SessionStatus sessionStatus) {
		bService.delete(bid);
		String redirectUrl=session.getAttribute("redirectUrl").toString();
		sessionStatus.setComplete();
		return redirectUrl;//夸没沁带 url傈价
	}
}
