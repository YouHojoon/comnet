package kr.ac.smu.cs.comnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.smu.cs.comnet.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService mService;
	
	@DeleteMapping
	public void delete(@RequestParam("mid") int mid) {
		mService.deleteMessage(mid);
	}
}
