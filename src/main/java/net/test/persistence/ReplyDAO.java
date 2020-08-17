package net.test.persistence;

import java.util.List;

import net.test.domain.Criteria;
import net.test.domain.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> list(Integer bno) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer rno) throws Exception;
	
	// 댓글 페이징 처리
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
	
	// 댓글 삭제시 게시물 번호 알아내는 메소드
	public int getBno(Integer rno) throws Exception;

}
