package kr.ac.smu.cs.comnet.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.protobuf.TextFormat.ParseException;

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
	public void update(@RequestParam("bid") int bid, Model model) {
		model.addAttribute("board", bService.select(bid));
		model.addAttribute("fieldList",fService.selectList());
		model.addAttribute("languageList", lService.selectList());
	}
	@PutMapping("/myproject")
	public @ResponseBody int update(@RequestParam("bid") int bid, @RequestBody Map<String, Object> json) throws java.text.ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date= new Date(sdf.parse((String)json.get("deadline")).getTime());
		BoardVO boardVO= new BoardVO(bid,(int)json.get("uid"),(String)json.get("title"),
				(String)json.get("content"),date,(int)json.get("partner_limit"),(String)json.get("contact"));
		bService.update(boardVO, (List<Integer>)json.get("boardField"), (List<Integer>)json.get("boardLanguage"));
		return 1;//반환 없으면 ajax success가 실행이 안되서 1반환
	}
}
