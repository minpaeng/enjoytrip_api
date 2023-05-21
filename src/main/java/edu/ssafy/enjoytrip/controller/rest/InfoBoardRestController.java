package edu.ssafy.enjoytrip.controller.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardCommentDto;
import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardCommentPageDto;
import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardDto;
import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardPageDto;
import edu.ssafy.enjoytrip.service.infoboard.InfoBoardCommentService;
import edu.ssafy.enjoytrip.service.infoboard.InfoBoardService;
import edu.ssafy.enjoytrip.util.SizeConstant;

@RestController
@RequestMapping("/api/information")
public class InfoBoardRestController {

	private InfoBoardService infoBoardService;
	private InfoBoardCommentService infoBoardCommentService;

	public InfoBoardRestController(InfoBoardService infoBoardService, InfoBoardCommentService infoBoardCommentService) {
		this.infoBoardService = infoBoardService;
		this.infoBoardCommentService = infoBoardCommentService;
	}

	// 공지사항 게시글 조회
	@GetMapping("/list")
	public InfoBoardPageDto list(@RequestParam Map<String, String> map) {
		List<InfoBoardDto> boardList = infoBoardService.list(map);
		int pageCount = infoBoardService.totalCount(map);
		if ((pageCount != 0) && (pageCount % SizeConstant.LIST_SIZE == 0)) pageCount /= SizeConstant.LIST_SIZE;
		else pageCount = pageCount / SizeConstant.LIST_SIZE + 1;
		return new InfoBoardPageDto(pageCount, boardList);
	}

	// 게시글 작성
	@PostMapping("/write")
	public ResponseEntity<String> write(@RequestBody Map<String, String> map, HttpSession session) {
		try {
//			MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
//			String userId = memberDto.getUserId();
			String userId = "ssafy";
			InfoBoardDto infoBoardDto = new InfoBoardDto();

			infoBoardDto.setUserId(userId);
			infoBoardDto.setTitle(map.get("title"));
			infoBoardDto.setContent(map.get("content"));
			infoBoardService.write(infoBoardDto);

			return new ResponseEntity<String>("공지사항 게시글 작성 성공", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("공지사항 게시글 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 게시글 상세 보기
	@GetMapping("/detail/{infoBoardId}")
	public InfoBoardDto detail(@PathVariable int infoBoardId) {
		return infoBoardService.select(infoBoardId);
	}

	// 게시글 수정
	@PutMapping("/modify/{infoBoardId}")
	public ResponseEntity<String> modify(@RequestBody Map<String, String> map, @PathVariable int infoBoardId) {
		try {
			InfoBoardDto infoBoardDto = new InfoBoardDto();
			infoBoardDto.setId(infoBoardId);
			infoBoardDto.setTitle(map.get("title"));
			infoBoardDto.setContent(map.get("content"));

			infoBoardService.modify(infoBoardDto);
			return new ResponseEntity<String>("공지사항 게시글 수정 성공", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("공지사항 게시글 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{infoBoardId}")
	public ResponseEntity<String> delete(@PathVariable int infoBoardId) {
		try {
			infoBoardCommentService.deleteByPost(infoBoardId);
			infoBoardService.delete(infoBoardId);
			return new ResponseEntity<String>("공지사항 게시글 삭제 성공", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("공지사항 게시글 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 특정 공지사항 게시글의 댓글 목록 조회
	@GetMapping("/{infoBoardId}/comment")
	public InfoBoardCommentPageDto comment(@PathVariable int infoBoardId, 
			@RequestParam(required = false, defaultValue = "1") int pgno) {
		int pageCount = infoBoardCommentService.totalCount(infoBoardId) / SizeConstant.LIST_SIZE + 1;
		List<InfoBoardCommentDto> list = infoBoardCommentService.list(infoBoardId);

		return new InfoBoardCommentPageDto(pageCount, list);
	}
	
	// 댓글 등록
	@PostMapping("/{infoBoardId}/comment")
	public ResponseEntity<String> createComment(@PathVariable int infoBoardId, 
			@RequestBody InfoBoardCommentDto commentDto){
		try {
			commentDto.setUserId("ssafy"); // 로그인 구현하게 되면 주석처리
			commentDto.setInfoBoardId(infoBoardId); // 로그인 구현하게 되면 주석처리
			infoBoardCommentService.write(commentDto);
			return new ResponseEntity<String>("공지사항 게시글 댓글 작성 성공", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("공지사항 게시글 댓글 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 댓글 수정
	@PutMapping("/comment")
	public ResponseEntity<String> updateComment(@RequestBody InfoBoardCommentDto commentDto){
		try {
			infoBoardCommentService.modify(commentDto);
			return new ResponseEntity<String>("공지사항 게시글 댓글 수정 성공", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("공지사항 게시글 댓글 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//댓글 삭제
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
		try {
			infoBoardCommentService.delete(commentId);
			return new ResponseEntity<String>("공지사항 게시글 댓글 삭제 성공", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("공지사항 게시글 댓글 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
