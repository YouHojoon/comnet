package kr.ac.smu.cs.comnet.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;
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
	public void newBoard(BoardVO boardVO, @RequestParam("board_field") int[] board_field, @RequestParam("board_language") int[] board_language) {
		bService.register(boardVO, board_field, board_language);
	}
}
