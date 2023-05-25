package edu.ssafy.enjoytrip.service.like;

import edu.ssafy.enjoytrip.dto.like.LikeDto;

public interface LikeService {
	void markLike(LikeDto likeDto, String userId);
	void cancelLike(LikeDto likeDto, String userId);
	boolean markStatus(LikeDto likeDto, String userId);
}
