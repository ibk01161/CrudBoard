package net.test.persistence;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;

public interface UserDAO {
	
	public UserVO login(LoginDTO dto) throws Exception;

}
