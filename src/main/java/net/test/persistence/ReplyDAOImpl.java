package net.test.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.test.domain.Criteria;
import net.test.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "net.test.mapper.ReplyMapper";
	

	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return session.selectList(namespace + ".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
		
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace + ".update", vo);
		
	}

	@Override
	public void delete(Integer rno) throws Exception {
		session.delete(namespace + ".delete", rno);
		
	}
	
	// 댓글 페이징 처리
	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace + ".listPage", paramMap);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace + ".count", bno);
	}

	// 댓글 삭제시 게시물 번호 알아내는 메소드
	@Override
	public int getBno(Integer rno) throws Exception {
		return session.selectOne(namespace + ".getBno", rno);
	}
	

}
