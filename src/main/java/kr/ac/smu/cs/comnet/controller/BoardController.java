package kr.ac.smu.cs.comnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	FieldService fService;
	@Autowired
	LanguageService lService;
	@GetMapping("/new")
	public void newBoard(Model model) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
	}
}
