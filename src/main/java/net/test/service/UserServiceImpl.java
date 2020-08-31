package net.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;
import net.test.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}
	
	

}
