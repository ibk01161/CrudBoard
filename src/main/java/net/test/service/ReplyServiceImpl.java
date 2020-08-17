package net.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.test.domain.Criteria;
import net.test.domain.ReplyVO;
import net.test.persistence.BoardDAO;
import net.test.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private BoardDAO boardDAO;

	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		replyDAO.create(vo);
		// 댓글 1증가
		boardDAO.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		return replyDAO.list(bno);
	}
	
	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		replyDAO.update(vo);
		
	}

	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {
		
		int bno = replyDAO.getBno(rno);
		replyDAO.delete(rno);
		// 댓글 1감소
		boardDAO.updateReplyCnt(bno, -1);
		
	}

	// 댓글 페이징 처리
	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		return replyDAO.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return replyDAO.count(bno);
	}
	
	

}
