package kr.ac.smu.cs.comnet.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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

import kr.ac.smu.cs.comnet.dto.BoardDTO;
import kr.ac.smu.cs.comnet.service.BoardService;
import kr.ac.smu.cs.comnet.service.FieldService;
import kr.ac.smu.cs.comnet.service.LanguageService;
import kr.ac.smu.cs.comnet.vo.BoardVO;

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
	@GetMapping("/myproject")
	public void myproject(@RequestParam("uid") int uid, Model model) {
		model.addAttribute("fieldList", fService.selectList());
		model.addAttribute("languageList", lService.selectList());
		List<BoardDTO> boardList= bService.selectMyProject(uid);
		model.addAttribute("boardList",boardList);
		if(boardList!=null)
			model.addAttribute("total",boardList.size());
		else
			model.addAttribute("total",0);
	}
}
