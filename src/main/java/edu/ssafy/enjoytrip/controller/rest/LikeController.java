package edu.ssafy.enjoytrip.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.enjoytrip.dto.BasicDto;
import edu.ssafy.enjoytrip.dto.StatusEnum;
import edu.ssafy.enjoytrip.dto.like.LikeDto;
import edu.ssafy.enjoytrip.jwt.JwtTokenProvider;
import edu.ssafy.enjoytrip.service.like.LikeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/great")
@RequiredArgsConstructor
public class LikeController {
	private final JwtTokenProvider tokenProvider;
	private final LikeService likeService;

	@PostMapping
	public ResponseEntity<BasicDto> markLike(@RequestBody LikeDto likeDto,  HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		String userId = tokenProvider.getUserPk(token);
		likeService.markLike(likeDto, userId);
		
		BasicDto response = BasicDto.builder()
				.status(StatusEnum.OK)
				.message("좋아요 표시 성공")
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}/{reviewId}")
	public ResponseEntity<BasicDto> deleteLike(@PathVariable String userId, @PathVariable int reviewId, 
			HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		String tokenUserId = tokenProvider.getUserPk(token);
		
		LikeDto likeDto = new LikeDto();
		likeDto.setReviewId(reviewId);
		likeDto.setUserId(userId);
		likeService.cancelLike(likeDto, tokenUserId);
		
		BasicDto response = BasicDto.builder()
				.status(StatusEnum.OK)
				.message("좋아요 취소 성공")
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}/{reviewId}")
	public ResponseEntity<BasicDto> checkLikeMarkStatus(@PathVariable String userId, @PathVariable int reviewId,
			HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		String tokenUserId = tokenProvider.getUserPk(token);
		
		BasicDto response;
		
		LikeDto likeDto = new LikeDto();
		likeDto.setReviewId(reviewId);
		likeDto.setUserId(userId);
		if (likeService.markStatus(likeDto, tokenUserId)) {
			response = BasicDto.builder()
					.status(StatusEnum.OK)
					.message("true")
					.build();
		}
		else {
			response = BasicDto.builder()
					.status(StatusEnum.OK)
					.message("false")
					.build();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
