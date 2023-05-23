package edu.ssafy.enjoytrip.service.review;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.ssafy.enjoytrip.dto.file.FileDto;
import edu.ssafy.enjoytrip.dto.file.FileResponseDto;
import edu.ssafy.enjoytrip.dto.file.ReviewFileDto;
import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import edu.ssafy.enjoytrip.dto.review.ReviewFileResponseDto;
import edu.ssafy.enjoytrip.dto.review.ReviewPageResponseDto;
import edu.ssafy.enjoytrip.dto.review.ReviewSaveRequestDto;
import edu.ssafy.enjoytrip.repository.file.ReviewFileRepository;
import edu.ssafy.enjoytrip.repository.review.ReviewRepository;
import edu.ssafy.enjoytrip.util.FileHandler;
import edu.ssafy.enjoytrip.util.SizeConstant;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	@Value("${view.path.review}")
    private String reviewImagePath;
	
	private final ReviewRepository reviewRepository;
	private final FileHandler fileHandler;
	private final ReviewFileRepository reviewFileRepository;
	
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

	@Override
	public ReviewPageResponseDto reviewList(int pgno) {
		// 총 페이지 수 조회
		int totalCount = reviewRepository.count();
		
		// offset 계산
		if ((totalCount != 0) && (totalCount % SizeConstant.LIST_SIZE == 0)) {
			totalCount /= SizeConstant.LIST_SIZE;
		}
		else totalCount = totalCount / SizeConstant.LIST_SIZE + 1;
		
		// 리뷰 조회
		List<ReviewDto> reviews = reviewRepository.list(pgno * totalCount, SizeConstant.LIST_SIZE);
		
		// 리뷰에 달린 사진 조회
		List<ReviewFileResponseDto> reviewFiles = new ArrayList<>();
		for (ReviewDto review : reviews) {			
			List<ReviewFileDto> files = reviewFileRepository.selectFileByReviewId(review.getReviewId());
			List<FileResponseDto> fileDtos = new ArrayList<>();
			for (ReviewFileDto file : files) {
				FileResponseDto fileDto = FileResponseDto.builder()
						.fid(file.getFid())
						.filePath(reviewImagePath + file.getPath())
						.build();
				fileDtos.add(fileDto);
			}
			ReviewFileResponseDto reviewFile = new ReviewFileResponseDto(review, fileDtos);
			
			reviewFiles.add(reviewFile);
		}
		
		// 페이지 객체
		return ReviewPageResponseDto.builder()
				.pageCount(totalCount)
				.reviews(reviewFiles)
				.build();
	}

}
