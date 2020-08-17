package net.test.service;

import net.test.domain.MessageVO;

public interface MessageService {
	
	public void addMessage(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(String uid, Integer mid) throws Exception;

}
