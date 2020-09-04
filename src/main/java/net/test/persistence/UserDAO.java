package net.test.persistence;

import java.util.Date;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;

public interface UserDAO {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
	// sessionkey, sessionLimit 업데이트
	public void keepLogin(String uid, String sessionId, Date next);
	
	// loginCookie값으로 사용자 정보 조회
	public UserVO checkUserWithSessionKey(String value);

}
