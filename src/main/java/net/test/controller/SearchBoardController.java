package net.test.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.test.domain.BoardVO;
import net.test.domain.PageMaker;
import net.test.domain.SearchCriteria;
import net.test.domain.UserVO;
import net.test.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	// 검색 기능
	@GetMapping("/list")
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpSession session) throws Exception {
		UserVO userVO = (UserVO) session.getAttribute("login");
		logger.info(cri.toString());
		
		//model.addAttribute("list",service.listCriteria(cri));
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(service.listCountCriteria(cri));
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker",pageMaker);
		model.addAttribute("userVO", userVO);
	}
	
	// 조회 처리
	@GetMapping("/readPage")
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("read");
		model.addAttribute(service.read(bno));
	}
	
	// 삭제 처리
	@PostMapping("/removePage")
	public String remove(@RequestParam("bno") int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(bno);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		rttr.addFlashAttribute("msg","success_remove");
		
		return "redirect:/sboard/list";
	}
	
	// 수정 처리 (폼)
	@GetMapping("/modifyPage")
	public void modifyGET(int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	
	// 수정 처리
	@PostMapping("/modifyPage")
	public String modifyPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info(cri.toString());
		service.modify(board);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		rttr.addFlashAttribute("msg","success_modify");
		
		logger.info(rttr.toString());
		
		return "redirect:/sboard/list";
	}
	
	// 등록 처리 (폼)
	@GetMapping("/register")
	public void registerGET() throws Exception {
		logger.info("regist get.....");
	}
	
	// 등록 처리
	@PostMapping("/register")
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("regist post.....");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/sboard/list";
	}
	
	// 첨부파일 처리 및 조회
	@RequestMapping("/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno")Integer bno) throws Exception {
		return service.getAttach(bno);
	}
	

}
