package net.test.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "net.test.mapper.PointMapper";

	@Override
	public void updatePoint(String uid, int point) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("point", point);
		
		session.update(namespace+".updatePoint", paramMap);
	}
	

}
