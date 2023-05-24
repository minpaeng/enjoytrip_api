package edu.ssafy.enjoytrip.service.review;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import edu.ssafy.enjoytrip.dto.review.ReviewFileResponseDto;
import edu.ssafy.enjoytrip.dto.review.ReviewPageResponseDto;
import edu.ssafy.enjoytrip.dto.review.ReviewSaveRequestDto;

public interface ReviewService {
	
	void createReview(ReviewSaveRequestDto requestDto, List<MultipartFile> files);
	
	ReviewPageResponseDto reviewList(int pgno);
	
	ReviewFileResponseDto getReveiwById(int reviewId);
	
	List<ReviewDto> top3Reviews();
}
