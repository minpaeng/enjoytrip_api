package edu.ssafy.enjoytrip.repository.review;

import java.util.List;

import edu.ssafy.enjoytrip.dto.review.ReviewCommentDto;

public interface ReviewCommentRepository {

	void write(ReviewCommentDto reviewCommentDto); // 댓글 작성
	ReviewCommentDto modify(ReviewCommentDto reviewCommentDto); // 댓글 수정
	void delete(String reviewCommentId); // 댓글 삭제
	List<ReviewCommentDto> list(String reviewId);
	
}
