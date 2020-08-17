package net.test.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.test.domain.BoardVO;
import net.test.domain.Criteria;
import net.test.domain.PageMaker;
import net.test.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	// 등록 페이지 (폼)
	@GetMapping("/register")
	public void registerGET(BoardVO board, Model model) throws Exception {
		logger.info("register get");
	}
	
	// 등록 페이지
	@PostMapping("/register")
	public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("regist post");
		logger.info(board.toString());
		
		service.regist(board);
		rttr.addFlashAttribute("msg","success");
		//return "/board/success";
		return "redirect:/board/listAll";
	}
	
	// 목록 페이지
	@GetMapping("/listAll")
	public void listAll(Model model) throws Exception {
		logger.info("show all list");
		model.addAttribute("list",service.listAll());
	}
	
	// 조회 페이지
	@GetMapping("/read")
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	
	// 삭제 처리
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		service.remove(bno);
		rttr.addFlashAttribute("msg","success_remove");
		return "redirect:/board/listAll";
	}
	
	// 수정 폼
	@GetMapping("/modify")
	public void modifyGET(int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	
	// 수정 처리
	@PostMapping("/modify")
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("mod post");
		
		service.modify(board);
		rttr.addFlashAttribute("msg","success_modi");
		return "redirect:/board/listAll";
	}
	
	// 페이징 처리
	@GetMapping("/listCri")
	public void listAll(Criteria cri, Model model) throws Exception {
		logger.info("show list Page");
		model.addAttribute("list",service.listCriteria(cri));
	}
	
	// 페이징 처리2
	@GetMapping("/listPage")
	public void listPage(Criteria cri, Model model) throws Exception {
		logger.info(cri.toString());
		
		model.addAttribute("list",service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(131);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker",pageMaker);
	}
	
	// 페이징 처리 후 조회 처리
	@GetMapping("/readPage")
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	
	// 페이징 처리 후 삭제 처리
	@PostMapping("/removePage")
	public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("msg","success_remove");
		
		return "redirect:/board/listPage";
	}
	
	// 페이징 처리 후 수정 폼
	@GetMapping("/modifyPage")
	public void modifyPagingGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	
	// 페이징 처리 후 수정 처리
	@PostMapping("/modifyPage")
	public String modifyPagingPOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {
		service.modify(board);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("msg","success_modify");
		
		return "redirect:/board/listPage";
	}

}
