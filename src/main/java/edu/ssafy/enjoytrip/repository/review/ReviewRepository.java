package edu.ssafy.enjoytrip.repository.review;

import java.util.List;

import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewRepository {

	ReviewDto modify(ReviewDto reviewDto); // 게시글 수정
	ReviewDto select(String reviewId); // 단건 조회
	List<ReviewDto> list(); // 목록 조회
	void delete(String reviewId); // 게시긇 삭제
	void write(ReviewDto reviewDto); // 게시글 작성
	void updateHit(String reivewId); // 조회수
	void updateLike(ReviewDto reviewDto); // 좋아요
	List<ReviewDto> topList(); // 좋아요 많은 녀석들, 굳이?인가

	
	
	
}
