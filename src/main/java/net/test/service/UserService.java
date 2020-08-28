package net.test.service;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;

public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;

}
