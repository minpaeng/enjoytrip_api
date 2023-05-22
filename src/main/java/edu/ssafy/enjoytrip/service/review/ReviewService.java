package edu.ssafy.enjoytrip.service.review;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.ssafy.enjoytrip.dto.review.ReviewSaveRequestDto;

public interface ReviewService {
	
	void createReview(ReviewSaveRequestDto requestDto, List<MultipartFile> files);
}
