package kr.ac.smu.cs.comnet.controller;

import java.awt.font.TransformAttribute;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;
import kr.ac.smu.cs.comnet.service.UserService;
import kr.ac.smu.cs.comnet.vo.BoardVO;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/board")
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
	public void view(@RequestParam("bid") int bid, Model model) {
		BoardDTO board = bService.select(bid);
		model.addAttribute("board", board);
		model.addAttribute("owner", uService.select(board.getBoardVO().getUid()));
	}
	
}
