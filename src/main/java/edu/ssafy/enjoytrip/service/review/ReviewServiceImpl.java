package edu.ssafy.enjoytrip.service.review;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.ssafy.enjoytrip.dto.file.FileResponseDto;
import edu.ssafy.enjoytrip.dto.file.ReviewFileDto;
import edu.ssafy.enjoytrip.dto.like.LikeCountDto;
import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import edu.ssafy.enjoytrip.dto.review.ReviewFileResponseDto;
import edu.ssafy.enjoytrip.dto.review.ReviewPageResponseDto;
import edu.ssafy.enjoytrip.dto.review.ReviewSaveRequestDto;
import edu.ssafy.enjoytrip.dto.review.ReviewTop3ResponseDto;
import edu.ssafy.enjoytrip.repository.file.ReviewFileRepository;
import edu.ssafy.enjoytrip.repository.like.LikeRepository;
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
	private final LikeRepository likeRepository;
	
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

	// 리뷰 리스트 페이징 조회
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
		List<ReviewDto> reviews = reviewRepository.list(pgno * SizeConstant.LIST_SIZE, SizeConstant.LIST_SIZE);
		
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
			int count = likeRepository.likeCount(review.getReviewId());
			ReviewFileResponseDto reviewFile = new ReviewFileResponseDto(review, count, fileDtos);
			
			reviewFiles.add(reviewFile);
		}
		
		// 페이지 객체
		return ReviewPageResponseDto.builder()
				.pageCount(totalCount)
				.reviews(reviewFiles)
				.build();
	}

	// 리뷰 단건 조회
	@Override
	public ReviewFileResponseDto getReveiwById(int reviewId) {
		// 리뷰 조회
		ReviewDto review =  reviewRepository.selectByReviewId(reviewId);
		
		// 리뷰에 달린 사진 조회
		List<ReviewFileDto> files = reviewFileRepository.selectFileByReviewId(reviewId);
		List<FileResponseDto> fileDtos = new ArrayList<>();
		for (ReviewFileDto file : files) {
			FileResponseDto fileDto = FileResponseDto.builder()
					.fid(file.getFid())
					.filePath(reviewImagePath + file.getPath())
					.build();
			fileDtos.add(fileDto);
		}
		
		reviewRepository.updateHit(reviewId);
		int count = likeRepository.likeCount(reviewId);

		return new ReviewFileResponseDto(review, count, fileDtos);
	}

	//top3 리뷰 리스트 조회
	@Override
	public List<ReviewTop3ResponseDto> top3Reviews() {
		// 좋아요 수가 높은 상위 3개 아이디 목록을 조회
		List<LikeCountDto> reviewIds = likeRepository.selectTop3ReviewIds();
		
		// 해당 아이디에 대해 리뷰 조회(in 쿼리 사용)
		List<ReviewDto> top3Reviews = reviewRepository.top3Reviews(reviewIds.stream()
																.map(t -> t.getReviewId())
																.collect(Collectors.toList()));
		
		List<ReviewTop3ResponseDto> response = new ArrayList<>();
		for (int i = 0; i < reviewIds.size(); i++) {
			ReviewDto review = top3Reviews.get(i);
			int count = likeRepository.likeCount(review.getReviewId());
			
			// 리뷰에 달린 사진 조회
			List<ReviewFileDto> files = reviewFileRepository.selectFileByReviewId(review.getReviewId());
			List<FileResponseDto> fileDtos = new ArrayList<>();
			for (ReviewFileDto file : files) {
				FileResponseDto fileDto = FileResponseDto.builder()
						.fid(file.getFid())
						.filePath(reviewImagePath + file.getPath())
						.build();
				fileDtos.add(fileDto);
			}
			
			ReviewTop3ResponseDto dto = new ReviewTop3ResponseDto(review, count, fileDtos);
			response.add(dto);
		}
		return response;
		
	}

}
