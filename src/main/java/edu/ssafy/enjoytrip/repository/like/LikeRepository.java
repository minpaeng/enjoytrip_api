package edu.ssafy.enjoytrip.repository.like;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.dto.like.LikeCountDto;
import edu.ssafy.enjoytrip.dto.review.ReviewDto;

@Mapper
public interface LikeRepository {
	List<LikeCountDto> selectTop3ReviewIds(); // top3 리뷰 아이디 3개 조회
	void createLike(ReviewDto reviewDto);
	void deleteLike(ReviewDto reviewDto);
}
