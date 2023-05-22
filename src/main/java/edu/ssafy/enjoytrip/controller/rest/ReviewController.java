package edu.ssafy.enjoytrip.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.ssafy.enjoytrip.dto.BasicDto;
import edu.ssafy.enjoytrip.dto.StatusEnum;
import edu.ssafy.enjoytrip.dto.review.ReviewSaveRequestDto;
import edu.ssafy.enjoytrip.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
	
	private final ReviewService reviewService;
	
	// 후기 작성
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<BasicDto> createReview(@RequestPart(name = "review") ReviewSaveRequestDto reviewSaveRequestDto,
							@RequestPart(name = "files", required = false) List<MultipartFile> files) {

		log.info(String.valueOf(reviewSaveRequestDto));
		
		try {
			reviewService.createReview(reviewSaveRequestDto, files);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
		
		BasicDto response = BasicDto.builder()
				.status(StatusEnum.OK)
				.message("후기 작성 성공")
				.data(reviewSaveRequestDto)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
