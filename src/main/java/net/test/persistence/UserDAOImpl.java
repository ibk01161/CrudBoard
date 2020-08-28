package net.test.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;

public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "net.test.mapper.UserMapper";

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace + ".login", dto);
	}
	
	

}
