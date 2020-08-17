package net.test.service;

import java.util.List;

import net.test.domain.Criteria;
import net.test.domain.ReplyVO;

public interface ReplyService {
	
	public void addReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listReply(Integer bno) throws Exception;
	
	public void modifyReply(ReplyVO vo) throws Exception;
	
	public void removeReply(Integer rno) throws Exception;
	
	// 댓글 페이징 처리
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;

}
