package edu.ssafy.enjoytrip.service.review;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import edu.ssafy.enjoytrip.dto.review.ReviewSaveRequestDto;
import edu.ssafy.enjoytrip.repository.review.ReviewRepository;
import edu.ssafy.enjoytrip.util.FileHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
	private final FileHandler fileHandler;
	
	// 리뷰 작성
	@Override
	@Transactional
	public void createReview(ReviewSaveRequestDto requestDto, List<MultipartFile> files) {
		// review dto 생성
		ReviewDto reviewDto = requestDto.toReviewDto();
		
		// 리뷰 등록
		reviewRepository.createReview(reviewDto);
		
		// 리뷰 아이디 조회
		int reviewId = reviewRepository.selectReviewIdByUserId(reviewDto.getUserId());
		
		// 첨부파일 처리
		fileHandler.parseFileInfo(files, reviewId);
	}

}
