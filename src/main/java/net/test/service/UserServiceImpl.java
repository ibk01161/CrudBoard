package net.test.service;

import org.springframework.beans.factory.annotation.Autowired;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;
import net.test.persistence.UserDAO;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}
	
	

}
