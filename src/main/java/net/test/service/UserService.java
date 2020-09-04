package net.test.service;

import java.util.Date;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;

public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next) throws Exception;
	
	public UserVO checkUserWithSessionKey(String value) throws Exception;

}
