package net.test.persistence;

import java.util.List;

import net.test.domain.BoardVO;
import net.test.domain.Criteria;
import net.test.domain.SearchCriteria;

public interface BoardDAO {
	
	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int countPaging(Criteria cri) throws Exception;
	
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	// 댓글 개수
	public void updateReplyCnt(Integer bno, int amount) throws Exception;
	
	// 조회수 처리
	public void updateViewCnt(Integer bno) throws Exception;
	
	// 첨부파일 처리
	public void addAttach(String fullName) throws Exception;
	
	// 게시물 조회 시 첨부파일 나타내기
	public List<String> getAttach(Integer bno) throws Exception;
	
	// 게시물 수정시 첨부파일
	public void deleteAttach(Integer bno) throws Exception;
	
	public void replaceAttach(String fullName, Integer bno) throws Exception;
 	

}
