package edu.ssafy.enjoytrip.repository.review;

import java.util.List;

import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewRepository {
	
	ReviewDto modify(ReviewDto reviewDto); // 리뷰 수정
	
	ReviewDto selectByReviewId(int reviewId); // 리뷰 단건 조회
	
	int selectReviewIdByUserId(String userId); // 사용자의 최신 리뷰 아이디 조회
	
	List<ReviewDto> top3Reviews(List<Integer> reviewIds);
	
	List<ReviewDto> list(int offset, int size); // 목록 조회
	
	void delete(int reviewId); // 리뷰 삭제
	
	void createReview(ReviewDto reviewDto); // 리뷰 작성
	
	void updateHit(int reviewId); // 조회수 증가
	
	int count(); // 총 데이터 수
}
