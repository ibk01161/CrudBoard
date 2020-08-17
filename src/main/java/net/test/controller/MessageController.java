package net.test.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.test.domain.MessageVO;
import net.test.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {
	
	@Inject
	private MessageService service;
	
	@PostMapping("/")
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			service.addMessage(vo);
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
