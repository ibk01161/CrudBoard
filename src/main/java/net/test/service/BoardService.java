package net.test.service;

import java.util.List;

import net.test.domain.BoardVO;
import net.test.domain.Criteria;
import net.test.domain.SearchCriteria;

public interface BoardService {
	
	public void regist(BoardVO board) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void modify(BoardVO board) throws Exception;
	
	public void remove(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int listCountCriteria(Criteria cri) throws Exception;
	
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	// 첨부파일 조회
	public List<String> getAttach(Integer bno) throws Exception;

}
