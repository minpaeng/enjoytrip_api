package edu.ssafy.enjoytrip.controller.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardCommentDto;
import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardDto;
import edu.ssafy.enjoytrip.dto.member.MemberDto;
import edu.ssafy.enjoytrip.service.infoboard.InfoBoardCommentService;
import edu.ssafy.enjoytrip.service.infoboard.InfoBoardService;
import edu.ssafy.enjoytrip.util.PageNavigation;
import edu.ssafy.enjoytrip.util.PageNavigationHandler;

@Controller
@RequestMapping("/information")
public class InfoBoardController {
	
	private InfoBoardService infoBoardService;
	private InfoBoardCommentService infoBoardCommentService;
	private PageNavigationHandler navigationHandler;
	
	public InfoBoardController(InfoBoardService infoBoardService,
			InfoBoardCommentService infoBoardCommentService,
			PageNavigationHandler navigationHandler) {
		this.infoBoardService = infoBoardService;
		this.infoBoardCommentService = infoBoardCommentService;
		this.navigationHandler = navigationHandler;
	}
	
	// 게시글 목록
	@GetMapping
	public ModelAndView list(@RequestParam Map<String, String> map, ModelAndView mav) {
		try {
			List<InfoBoardDto> list = infoBoardService.list(map);
			
			int totalCount = infoBoardService.totalCount(map);
			int currentPage = Integer.valueOf(map.get("pgno"));
			PageNavigation pageNavigation = navigationHandler.makePageNavigation(currentPage, totalCount);

			mav.addObject("posts", list);
			mav.addObject("navigation", pageNavigation);
			mav.setViewName("information/information");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "게시글 목록 보기 오류");
			mav.setViewName("error/error");
		}

		return mav;
	}
	
	// 게시글 작성
	@GetMapping("/write")
	public String write() {
		return "information/write";
	}
	
	@PostMapping("/write")
	public ModelAndView write(@RequestParam Map<String, String> map, 
							HttpSession session, ModelAndView mav) {
		try {
			MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
			String userId = memberDto.getUserId();
			InfoBoardDto infoBoardDto = new InfoBoardDto();
			
			infoBoardDto.setUserId(userId);
			infoBoardDto.setTitle(map.get("title"));
			infoBoardDto.setContent(map.get("content"));
			infoBoardService.write(infoBoardDto);
			
			mav.setViewName("redirect:/information?pgno=1");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "게시글 작성 실패");
			mav.setViewName("error/error");
		}
		return mav;
	}
	
	// 게시글 상세 보기
	@GetMapping("/detail/{infoBoardId}")
	public ModelAndView detail(@PathVariable int infoBoardId, ModelAndView mav) {
		try {
			InfoBoardDto infoBoardDto = infoBoardService.select(infoBoardId);
			infoBoardService.updateHit(infoBoardId);
			
			List<InfoBoardCommentDto> comments = infoBoardCommentService.list(infoBoardId);

			mav.addObject("post", infoBoardDto);
			mav.addObject("comments", comments);
			mav.setViewName("information/detail");		
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "상세 페이지 보기 오류");
			mav.setViewName("error/error");
		}
		return mav;
	}
	
	// 게시글 수정
	@GetMapping("/modify/{infoBoardId}")
	public ModelAndView modify(@PathVariable int infoBoardId, ModelAndView mav) {
		InfoBoardDto infoBoardDto = infoBoardService.select(infoBoardId);
		mav.addObject("post", infoBoardDto);
		mav.setViewName("information/modify");
		return mav;
	}
	
	@PostMapping("/modify/{infoBoardId}")
	public ModelAndView modify(@RequestParam Map<String, String> map, ModelAndView mav) {
		int infoBoardId = Integer.valueOf(map.get("id"));
		
		InfoBoardDto infoBoardDto = new InfoBoardDto();
		infoBoardDto.setId(infoBoardId);
		infoBoardDto.setTitle(map.get("title"));
		infoBoardDto.setContent(map.get("content"));
		
		infoBoardService.modify(infoBoardDto);
		
		mav.addObject("post", infoBoardDto);
		mav.setViewName("redirect:/information/detail/" + infoBoardId);
		return mav;
	}
	
	// 게시글 삭제
	@GetMapping("/delete/{infoBoardId}")
	public String delete(@PathVariable int infoBoardId) {
		infoBoardCommentService.deleteByPost(infoBoardId);
		infoBoardService.delete(infoBoardId);
		return "redirect:/information?pgno=1";
	}
	
	// 댓글
	@PostMapping("/{infoBoardId}/comment")
	public String comment(@RequestParam String content, 
						  @PathVariable int infoBoardId,
						  HttpSession session,
						  Model model) {
		MemberDto member = (MemberDto) session.getAttribute("userInfo");
		if (member != null) {
			InfoBoardCommentDto comment = new InfoBoardCommentDto();
			comment.setInfoBoardId(infoBoardId);
			comment.setUserId(member.getUserId());
			comment.setContent(content);
			infoBoardCommentService.write(comment);
			return "redirect:/information/detail/" + infoBoardId;
		}
		else {
			model.addAttribute("msg", "댓글 등록 오류");
			return "error/error";
		}
	}
	
	@GetMapping("/{infoBoardId}/comment/{commentId}")
	public String comment(@PathVariable int infoBoardId,
						  @PathVariable int commentId,
						  Model model) {
		infoBoardCommentService.delete(commentId);
		return "redirect:/information/detail/" + infoBoardId;
	}
}
